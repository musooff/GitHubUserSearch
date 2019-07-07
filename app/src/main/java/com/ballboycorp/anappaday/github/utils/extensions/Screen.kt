package com.ballboycorp.anappaday.github.utils.extensions

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


/**
 * Created by musooff on 2019-07-07.
 */

fun EditText.hideKeyboard() {
    clearFocus()
    val inputMethod = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethod.hideSoftInputFromWindow(windowToken, 0)
}