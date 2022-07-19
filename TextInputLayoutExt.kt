package com.elad.finish.utility.extensions

import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.enableAndSetError(@StringRes resId: Int) {
    isErrorEnabled = true
    error = context.getString(resId)
}

fun TextInputLayout.resetError() {
    isErrorEnabled = false
    error = null
}

private fun TextInputLayout.hasPasswordTransformation() =
    editText?.transformationMethod is PasswordTransformationMethod

fun TextInputLayout.addPasswordToggleIconLongClickListeners(onPlainText: () -> Unit, onDisguised: () -> Unit) {
    if (endIconMode == TextInputLayout.END_ICON_PASSWORD_TOGGLE) {
        setEndIconOnLongClickListener {
            if (hasPasswordTransformation()) {
                onDisguised()
            } else {
                onPlainText()
            }
            false
        }
    }
}

fun TextInputLayout.showHintsOnPasswordToggleLongClick() {
    addPasswordToggleIconLongClickListeners(
        onPlainText = { Toast.makeText(requireContext(), "Hide password", Toast.LENGTH_SHORT).show() },
        onDisguised = { Toast.makeText(requireContext(), "Show password", Toast.LENGTH_SHORT).show() }
    )
}