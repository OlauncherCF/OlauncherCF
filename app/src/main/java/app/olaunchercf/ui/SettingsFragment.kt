package app.olaunchercf.ui

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.olaunchercf.BuildConfig
import app.olaunchercf.MainActivity
import app.olaunchercf.MainViewModel
import app.olaunchercf.R
import app.olaunchercf.data.Constants
import app.olaunchercf.data.Prefs
import app.olaunchercf.databinding.FragmentSettingsBinding
import app.olaunchercf.helper.*
import app.olaunchercf.listener.DeviceAdmin

class SettingsFragment : Fragment(), View.OnClickListener, View.OnLongClickListener {

    private lateinit var prefs: Prefs
    private lateinit var viewModel: MainViewModel
    private lateinit var deviceManager: DevicePolicyManager
    private lateinit var componentName: ComponentName

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // return inflater.inflate(R.layout.fragment_settings, container, false)
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prefs = Prefs(requireContext())
        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        viewModel.isOlauncherDefault()

        deviceManager = context?.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        componentName = ComponentName(requireContext(), DeviceAdmin::class.java)
        checkAdminPermission()

        binding.homeAppsNum.text = prefs.homeAppsNum.toString()
        populateKeyboardText()
        populateLockSettings()
        populateAppThemeText()
        populateAlignment()
        populateLanguageText()
        populateTextSizeText()
        populateStatusBar()
        populateDateTime()
        populateSwipeApps()
        populateClickApps()
        initClickListeners()
        initObservers()
    }

    override fun onClick(view: View) {
        binding.appsNumSelectLayout.visibility = View.GONE
        binding.alignmentSelectLayout.visibility = View.GONE
        binding.appThemeSelectLayout.visibility = View.GONE
        binding.appLangSelectLayout.visibility = View.GONE
        binding.textSizeLayout.visibility = View.GONE

        when (view.id) {
            R.id.olauncherHiddenApps -> showHiddenApps()
            R.id.appInfo -> openAppInfo(requireContext(), android.os.Process.myUserHandle(), BuildConfig.APPLICATION_ID)
            R.id.setLauncher -> viewModel.resetDefaultLauncherApp(requireContext())
            R.id.toggleLock -> toggleLockMode()
            R.id.autoShowKeyboard -> toggleKeyboardText()
            R.id.homeAppsNum -> binding.appsNumSelectLayout.visibility = View.VISIBLE
            R.id.alignment -> binding.alignmentSelectLayout.visibility = View.VISIBLE
            R.id.alignmentLeft -> viewModel.updateHomeAlignment(Gravity.START)
            R.id.alignmentCenter -> viewModel.updateHomeAlignment(Gravity.CENTER)
            R.id.alignmentRight -> viewModel.updateHomeAlignment(Gravity.END)
            R.id.statusBar -> toggleStatusBar()
            R.id.dateTime -> toggleDateTime()
            R.id.appThemeText -> binding.appThemeSelectLayout.visibility = View.VISIBLE
            R.id.themeLight -> updateTheme(AppCompatDelegate.MODE_NIGHT_NO)
            R.id.themeDark -> updateTheme(AppCompatDelegate.MODE_NIGHT_YES)
            R.id.appLangText -> binding.appLangSelectLayout.visibility = View.VISIBLE
            R.id.langEn -> setLang(Constants.LANG_EN)
            R.id.langDe -> setLang(Constants.LANG_DE)
            R.id.langEs -> setLang(Constants.LANG_ES)
            R.id.langFr -> setLang(Constants.LANG_FR)
            R.id.langIt -> setLang(Constants.LANG_IT)
            R.id.langSe -> setLang(Constants.LANG_SE)
            R.id.langTr -> setLang(Constants.LANG_TR)

            R.id.textSizeText -> binding.textSizeLayout.visibility = View.VISIBLE
            R.id.textSizeHuge -> setTextSize(Constants.TEXT_SIZE_HUGE)
            R.id.textSizeNormal -> setTextSize(Constants.TEXT_SIZE_NORMAL)
            R.id.textSizeSmall -> setTextSize(Constants.TEXT_SIZE_SMALL)

            R.id.maxApps0 -> updateHomeAppsNum(0)
            R.id.maxApps1 -> updateHomeAppsNum(1)
            R.id.maxApps2 -> updateHomeAppsNum(2)
            R.id.maxApps3 -> updateHomeAppsNum(3)
            R.id.maxApps4 -> updateHomeAppsNum(4)
            R.id.maxApps5 -> updateHomeAppsNum(5)
            R.id.maxApps6 -> updateHomeAppsNum(6)
            R.id.maxApps7 -> updateHomeAppsNum(7)
            R.id.maxApps8 -> updateHomeAppsNum(8)

            R.id.swipeLeftApp -> showAppListIfEnabled(Constants.FLAG_SET_SWIPE_LEFT_APP)
            R.id.swipeRightApp -> showAppListIfEnabled(Constants.FLAG_SET_SWIPE_RIGHT_APP)
            R.id.clockClickApp -> showAppListIfEnabled(Constants.FLAG_SET_CLICK_CLOCK_APP)
            R.id.dateClickApp -> showAppListIfEnabled(Constants.FLAG_SET_CLICK_DATE_APP)
        }
    }

    override fun onLongClick(view: View): Boolean {
        when (view.id) {
            R.id.alignment -> {
                prefs.appLabelAlignment = prefs.homeAlignment
                findNavController().navigate(R.id.action_settingsFragment_to_appListFragment)
            }
            R.id.appThemeText -> updateTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            R.id.swipeLeftApp -> toggleSwipeLeft()
            R.id.swipeRightApp -> toggleSwipeRight()
            R.id.toggleLock -> {
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                deviceManager.removeActiveAdmin(componentName) // for backward compatibility
            }
        }
        return true
    }

    private fun initClickListeners() {
        binding.olauncherHiddenApps.setOnClickListener(this)
        binding.scrollLayout.setOnClickListener(this)
        binding.appInfo.setOnClickListener(this)
        binding.setLauncher.setOnClickListener(this)
        binding.autoShowKeyboard.setOnClickListener(this)
        binding.toggleLock.setOnClickListener(this)
        binding.homeAppsNum.setOnClickListener(this)
        binding.alignment.setOnClickListener(this)
        binding.alignmentLeft.setOnClickListener(this)
        binding.alignmentCenter.setOnClickListener(this)
        binding.alignmentRight.setOnClickListener(this)
        binding.statusBar.setOnClickListener(this)
        binding.dateTime.setOnClickListener(this)
        binding.swipeLeftApp.setOnClickListener(this)
        binding.swipeRightApp.setOnClickListener(this)

        binding.clockClickApp.setOnClickListener(this)
        binding.dateClickApp.setOnClickListener(this)

        binding.appThemeText.setOnClickListener(this)
        binding.themeLight.setOnClickListener(this)
        binding.themeDark.setOnClickListener(this)

        binding.appLangText.setOnClickListener(this)
        binding.langEn.setOnClickListener(this)
        binding.langDe.setOnClickListener(this)
        binding.langEs.setOnClickListener(this)
        binding.langFr.setOnClickListener(this)
        binding.langIt.setOnClickListener(this)
        binding.langSe.setOnClickListener(this)
        binding.langTr.setOnClickListener(this)

        binding.textSizeText.setOnClickListener(this)
        binding.textSizeHuge.setOnClickListener(this)
        binding.textSizeNormal.setOnClickListener(this)
        binding.textSizeSmall.setOnClickListener(this)

        binding.maxApps0.setOnClickListener(this)
        binding.maxApps1.setOnClickListener(this)
        binding.maxApps2.setOnClickListener(this)
        binding.maxApps3.setOnClickListener(this)
        binding.maxApps4.setOnClickListener(this)
        binding.maxApps5.setOnClickListener(this)
        binding.maxApps6.setOnClickListener(this)
        binding.maxApps7.setOnClickListener(this)
        binding.maxApps8.setOnClickListener(this)

        binding.alignment.setOnLongClickListener(this)
        binding.appThemeText.setOnLongClickListener(this)
        binding.swipeLeftApp.setOnLongClickListener(this)
        binding.swipeRightApp.setOnLongClickListener(this)
        binding.toggleLock.setOnLongClickListener(this)
    }

    private fun initObservers() {
        if (prefs.firstSettingsOpen) {
            prefs.firstSettingsOpen = false
        }
        viewModel.isOlauncherDefault.observe(viewLifecycleOwner) {
            if (it) {
                binding.setLauncher.text = getString(R.string.change_default_launcher)
                prefs.toShowHintCounter = prefs.toShowHintCounter + 1
            }
        }
        viewModel.homeAppAlignment.observe(viewLifecycleOwner) {
            populateAlignment()
        }
        viewModel.updateSwipeApps.observe(viewLifecycleOwner) {
            populateSwipeApps()
        }
        viewModel.updateClickApps.observe(viewLifecycleOwner) {
            populateClickApps()
        }
    }

    private fun toggleSwipeLeft() {
        prefs.swipeLeftEnabled = !prefs.swipeLeftEnabled
        if (prefs.swipeLeftEnabled) {
            binding.swipeLeftApp.setTextColor(requireContext().getColorFromAttr(R.attr.primaryColor))
            showToastShort(requireContext(), "Swipe left app enabled")
        } else {
            binding.swipeLeftApp.setTextColor(requireContext().getColorFromAttr(R.attr.primaryColorTrans50))
            showToastShort(requireContext(), "Swipe left app disabled")
        }
    }

    private fun toggleSwipeRight() {
        prefs.swipeRightEnabled = !prefs.swipeRightEnabled
        if (prefs.swipeRightEnabled) {
            binding.swipeRightApp.setTextColor(requireContext().getColorFromAttr(R.attr.primaryColor))
            showToastShort(requireContext(), "Swipe right app enabled")
        } else {
            binding.swipeRightApp.setTextColor(requireContext().getColorFromAttr(R.attr.primaryColorTrans50))
            showToastShort(requireContext(), "Swipe right app disabled")
        }
    }

    private fun toggleStatusBar() {
        prefs.showStatusBar = !prefs.showStatusBar
        populateStatusBar()
    }

    private fun populateStatusBar() {
        if (prefs.showStatusBar) {
            showStatusBar()
            binding.statusBar.text = getString(R.string.on)
        } else {
            hideStatusBar()
            binding.statusBar.text = getString(R.string.off)
        }
    }

    private fun toggleDateTime() {
        prefs.showDateTime = !prefs.showDateTime
        populateDateTime()
        viewModel.toggleDateTime(prefs.showDateTime)
    }

    private fun populateDateTime() {
        if (prefs.showDateTime) binding.dateTime.text = getString(R.string.on)
        else binding.dateTime.text = getString(R.string.off)
    }

    private fun showStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            requireActivity().window.insetsController?.show(WindowInsets.Type.statusBars())
        else
            @Suppress("DEPRECATION", "InlinedApi")
            requireActivity().window.decorView.apply {
                systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
    }

    private fun hideStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            requireActivity().window.insetsController?.hide(WindowInsets.Type.statusBars())
        else {
            @Suppress("DEPRECATION")
            requireActivity().window.decorView.apply {
                systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE or View.SYSTEM_UI_FLAG_FULLSCREEN
            }
        }
    }

    private fun showHiddenApps() {
        if (prefs.hiddenApps.isEmpty()) {
            showToastShort(requireContext(), "No hidden apps")
            return
        }
        viewModel.getHiddenApps()
        findNavController().navigate(
            R.id.action_settingsFragment_to_appListFragment,
            bundleOf("flag" to Constants.FLAG_HIDDEN_APPS)
        )
    }

    private fun checkAdminPermission() {
        val isAdmin: Boolean = deviceManager.isAdminActive(componentName)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P)
            prefs.lockModeOn = isAdmin
    }

    private fun toggleLockMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            when {
                prefs.lockModeOn -> {
                    prefs.lockModeOn = false
                    deviceManager.removeActiveAdmin(componentName) // for backward compatibility
                }
                isAccessServiceEnabled(requireContext()) -> prefs.lockModeOn = true
                else -> {
                    showToastLong(requireContext(), "Please turn on accessibility service for Olauncher")
                    startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                }
            }
        } else {
            val isAdmin: Boolean = deviceManager.isAdminActive(componentName)
            if (isAdmin) {
                deviceManager.removeActiveAdmin(componentName)
                prefs.lockModeOn = false
                showToastShort(requireContext(), "Admin permission removed.")
            } else {
                val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName)
                intent.putExtra(
                    DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                    getString(R.string.admin_permission_message)
                )
                activity?.startActivityForResult(intent, Constants.REQUEST_CODE_ENABLE_ADMIN)
            }
        }
        populateLockSettings()
    }

    private fun updateHomeAppsNum(num: Int) {
        binding.homeAppsNum.text = num.toString()
        binding.appsNumSelectLayout.visibility = View.GONE
        prefs.homeAppsNum = num
        viewModel.refreshHome(true)
    }

    private fun toggleKeyboardText() {
        prefs.autoShowKeyboard = !prefs.autoShowKeyboard
        populateKeyboardText()
    }

    private fun updateTheme(appTheme: Int) {
        if (AppCompatDelegate.getDefaultNightMode() == appTheme) return
        prefs.appTheme = appTheme
        populateAppThemeText(appTheme)
        setAppTheme(appTheme)
    }

    private fun setLang(lang: String) {
        prefs.language = lang
        populateLanguageText(lang)

        // restart activity
        activity?.let {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            it.startActivity(intent)
            it.finish()
        }
    }
    private fun setTextSize(size: Float) {
        if (size == Constants.TEXT_SIZE_HUGE || size == Constants.TEXT_SIZE_NORMAL || size == Constants.TEXT_SIZE_SMALL) {
            prefs.textSize = size

            populateTextSizeText(size)

            // restart activity
            activity?.let {
                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                it.startActivity(intent)
                it.finish()
            }
        }
    }

    private fun setAppTheme(theme: Int) {
        if (AppCompatDelegate.getDefaultNightMode() == theme) return

        requireActivity().recreate()
    }

    private fun populateAppThemeText(appTheme: Int = prefs.appTheme) {
        when (appTheme) {
            AppCompatDelegate.MODE_NIGHT_YES -> binding.appThemeText.text = getString(R.string.dark)
            AppCompatDelegate.MODE_NIGHT_NO -> binding.appThemeText.text = getString(R.string.light)
            else -> binding.appThemeText.text = getString(R.string.system_default)
        }
    }

    private fun populateLanguageText(language: String = prefs.language) {
        when (language) {
            Constants.LANG_DE -> binding.appLangText.text = getString(R.string.lang_de)
            Constants.LANG_ES -> binding.appLangText.text = getString(R.string.lang_es)
            Constants.LANG_FR -> binding.appLangText.text = getString(R.string.lang_fr)
            Constants.LANG_IT -> binding.appLangText.text = getString(R.string.lang_it)
            Constants.LANG_SE -> binding.appLangText.text = getString(R.string.lang_se)
            Constants.LANG_TR -> binding.appLangText.text = getString(R.string.lang_tr)
            else -> binding.appLangText.text = getString(R.string.lang_en)
        }
    }

    private fun populateTextSizeText(size: Float = prefs.textSize) {
        when(size) {
            Constants.TEXT_SIZE_HUGE -> binding.textSizeText.text = getString(R.string.text_size_huge)
            Constants.TEXT_SIZE_NORMAL -> binding.textSizeText.text = getString(R.string.text_size_normal)
            Constants.TEXT_SIZE_SMALL -> binding.textSizeText.text = getString(R.string.text_size_small)
        }
    }

    private fun populateKeyboardText() {
        if (prefs.autoShowKeyboard) binding.autoShowKeyboard.text = getString(R.string.on)
        else binding.autoShowKeyboard.text = getString(R.string.off)
    }

    private fun populateAlignment() {
        when (prefs.homeAlignment) {
            Gravity.START -> binding.alignment.text = getString(R.string.left)
            Gravity.CENTER -> binding.alignment.text = getString(R.string.center)
            Gravity.END -> binding.alignment.text = getString(R.string.right)
        }
    }

    private fun populateLockSettings() {
        if (prefs.lockModeOn) binding.toggleLock.text = getString(R.string.on)
        else binding.toggleLock.text = getString(R.string.off)
    }

    private fun populateSwipeApps() {
        binding.swipeLeftApp.text = prefs.appNameSwipeLeft
        binding.swipeRightApp.text = prefs.appNameSwipeRight
        if (!prefs.swipeLeftEnabled)
            binding.swipeLeftApp.setTextColor(requireContext().getColorFromAttr(R.attr.primaryColorTrans50))
        if (!prefs.swipeRightEnabled)
            binding.swipeRightApp.setTextColor(requireContext().getColorFromAttr(R.attr.primaryColorTrans50))
    }
    private fun populateClickApps() {
        binding.clockClickApp.text = prefs.appNameClickClock
        binding.dateClickApp.text = prefs.appNameClickDate
    }

    private fun showAppListIfEnabled(flag: Int) {
        if ((flag == Constants.FLAG_SET_SWIPE_LEFT_APP) and !prefs.swipeLeftEnabled) {
            showToastShort(requireContext(), "Long press to enable")
            return
        }
        if ((flag == Constants.FLAG_SET_SWIPE_RIGHT_APP) and !prefs.swipeRightEnabled) {
            showToastShort(requireContext(), "Long press to enable")
            return
        }

        viewModel.getAppList()
        findNavController().navigate(
            R.id.action_settingsFragment_to_appListFragment,
            bundleOf("flag" to flag)
        )
    }
}