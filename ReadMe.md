## TangramDialog
Android 对话框，基于DialogFragment，可自定义布局及动画，可指定弹出位置(顶部、居中、底部)  

![TangramDialog](TangramDialog.webp)  

![](TangramDialog_Gif.gif)  

### 添加依赖
#### Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:  

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}  

#### Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.EthanCo:TangramDialog:1.0.5'
	}

### 使用
#### 基本使用

	TangramDialog dialog = new TangramDialog.Builder(MainActivity.this)
		.title("标题")
		.content("这是具体内容")
		.positiveText("确定")
		.negativeText("取消")
		.canceledOnTouchOutside(true)
		.onPositive(new ButtonCallback() {
		    @Override
		    public void onClick(@NonNull DialogBase dialog, @NonNull View view) {
		        //确定
		    }
		})
		.onNegative(new ButtonCallback() {
		    @Override
		    public void onClick(@NonNull DialogBase dialog, @NonNull View view) {
		        //取消
		    }
		})
		.show();

#### 具体方法

	/**
         * 设置标题
         *
         * @param title
         * @return
         */
        public Builder title(CharSequence title)

        /**
         * 设置标题
         *
         * @param titleRes
         * @return
         */
        public Builder title(@StringRes int titleRes)

        /**
         * 标题颜色
         *
         * @param colorRes
         * @return
         */
        public Builder titleColorRes(@ColorRes int colorRes)

        /**
         * 标题字体大小
         *
         * @param textSize
         * @return
         */
        public Builder titleTextSize(float textSize)

        /**
         * 内容文字
         *
         * @param content
         * @return
         */
        public Builder content(CharSequence content)

        /**
         * 内容文字
         *
         * @param contentRes
         * @return
         */
        public Builder content(@StringRes int contentRes)

        /**
         * 内容文字颜色
         *
         * @param colorRes
         * @return
         */
        public Builder contentColorRes(@ColorRes int colorRes)

        /**
         * 内容文字字体
         *
         * @param textSize
         * @return
         */
        public Builder contentTextSize(float textSize)

        /**
         * 图片资源ID
         *
         * @param imgRes
         * @return
         */
        public Builder imgRes(@DrawableRes int imgRes)

        /**
         * 对话框布局，用作替换默认布局样式，id需要和默认布局保持一致
         *
         * @param layoutId
         * @return
         */
        public Builder layoutId(int layoutId)

        /**
         * 否定的按钮文字
         *
         * @param message
         * @return
         */
        public Builder negativeText(CharSequence message)

        /**
         * 否定的按钮文字
         *
         * @param messageRes
         * @return
         */
        public Builder negativeText(@StringRes int messageRes)

        /**
         * 中立的按钮文字
         *
         * @param message
         * @return
         */
        public Builder neutralText(CharSequence message)

        /**
         * 中立的按钮文字
         *
         * @param messageRes
         * @return
         */
        public Builder neutralText(@StringRes int messageRes)

        /**
         * 肯定的按钮文字
         *
         * @param message
         * @return
         */
        public Builder positiveText(CharSequence message)

        /**
         * 肯定的按钮文字
         *
         * @param messageRes
         * @return
         */
        public Builder positiveText(@StringRes int messageRes)

        /**
         * 对话框位置
         *
         * @param gravity {@link android.view.Gravity#TOP}:顶部
         *                {@link android.view.Gravity#CENTER}:居中
         *                {@link android.view.Gravity#BOTTOM}:底部
         * @return
         */
        public Builder gravity(int gravity)

        /**
         * 对话框 显示/隐藏 动画
         *
         * @param animStyle
         * @return
         */
        public Builder animStyle(int animStyle)

        /**
         * 指定对话框宽度
         *
         * @param width
         * @return
         */
        public Builder width(int width)

        /**
         * 指定对话框高度
         *
         * @param height
         * @return
         */
        public Builder height(int height)

        /**
         * 肯定的按钮 点击回调
         *
         * @param callback
         * @return
         */
        public Builder onPositive(@NonNull ButtonCallback callback)

        /**
         * 中立的按钮 点击回调
         *
         * @param callback
         * @return
         */
        public Builder onNeutral(@NonNull ButtonCallback callback)

        /**
         * 否定的按钮 点击回调
         *
         * @param callback
         * @return
         */
        public Builder onNegative(@NonNull ButtonCallback callback)

        /**
         * 对话框外围 点击是否可隐藏
         *
         * @param canceledOnTouchOutside
         * @return
         */
        public Builder canceledOnTouchOutside(boolean canceledOnTouchOutside)

        /**
         * 对话框 margin
         *
         * @param margin
         * @return
         */
        public Builder margin(int margin)

        /**
         * 背景的昏暗度
         *
         * @param dimAmount
         * @return
         */
        public Builder dimAmount(@FloatRange(from = 0, to = 1) float dimAmount)

        /**
         * 设置背景
         * 例如 new ColorDrawable(Color.BLUE) //蓝色
         * new ColorDrawable(Color.TRANSPARENT) //透明
         *
         * @param drawable
         * @return
         */
        public Builder background(Drawable drawable)

        /**
         * 设置背景色
         *
         * @param backgroundColor
         * @return
         */
        public Builder backgroundColor(@ColorRes int backgroundColor)

        /**
         * 设置自定义的View，此模式下需手动findViewById来设置UI及相关点击回调
         * 通过{@link DialogBase#getRootView()}获取根View
         *
         * @param customView
         * @return
         */
        public Builder customView(View customView)

        /**
         * 设置自定义的View，此模式下需手动findViewById来设置UI及相关点击回调
         * 通过{@link DialogBase#getRootView()}获取根View
         *
         * @param layoutRes
         * @return
         */
        public Builder customView(@LayoutRes int layoutRes)

        /**
         * 点击按钮是否自动隐藏对话框
         *
         * @param dismiss
         * @return
         */
        public Builder autoDismiss(boolean dismiss)

        /**
         * 对话框相对自身位置X轴的偏移量
         *
         * @param offsetX
         * @return
         */
        public Builder offsetX(int offsetX)

        /**
         * 对话框相对自身位置Y轴的偏移量
         *
         * @param offsetY
         * @return
         */
        public Builder offsetY(int offsetY)

        /**
         * 点击背景 点击事件是否穿透
         *
         * @param canPenetrate
         * @return
         */
        public Builder canPenetrate(boolean canPenetrate)

        /**
         * 显示输入框
         *
         * @param hint     hint文字
         * @param prefill  预加载的文字
         * @param callback 输入改变回调
         * @return
         */
        public Builder input(
                @Nullable CharSequence hint,
                @Nullable CharSequence prefill,
                @NonNull InputCallback callback)

        /**
         * 显示输入框
         *
         * @param hint            hint文字
         * @param prefill         预加载的文字
         * @param allowEmptyInput 是否允许为空
         * @param callback        输入改变回调
         * @return
         */
        public Builder input(
                @Nullable CharSequence hint,
                @Nullable CharSequence prefill,
                boolean allowEmptyInput,
                @NonNull InputCallback callback)

        /**
         * 显示输入框
         *
         * @param hint            hint文字
         * @param prefill         预加载的文字
         * @param allowEmptyInput 是否允许为空
         * @param callback        输入改变回调
         * @return
         */
        public Builder input(
                @StringRes int hint,
                @StringRes int prefill,
                boolean allowEmptyInput,
                @NonNull InputCallback callback)

        /**
         * 显示输入框
         *
         * @param hint     hint文字
         * @param prefill  预加载的文字
         * @param callback 输入改变回调
         * @return
         */
        public Builder input(
                @StringRes int hint, 
				@StringRes int prefill,
				@NonNull InputCallback callback)