package com.ballboycorp.anappaday.github.common

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * Created by musooff on 2019-07-05.
 */

open class PagedNetworkResult<T>(
        var data: LiveData<PagedList<T>>, var state: LiveData<NullPointerException>
)