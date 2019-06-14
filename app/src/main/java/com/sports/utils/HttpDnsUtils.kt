package com.sports.utils

import android.app.Application
import com.alibaba.sdk.android.httpdns.HttpDns
import com.alibaba.sdk.android.httpdns.HttpDnsService
import okhttp3.Dns
import java.net.InetAddress


lateinit var httpDns: HttpDnsService

fun Application.initHttpDns() {
    httpDns = HttpDns.getService(this, "153956", "7b26b647bc244b13e7db327b80277e06").apply {
        setPreResolveAfterNetworkChanged(true)
        setPreResolveHosts(arrayListOf("api.cdeclips.com"))
        setExpiredIPEnabled(true)
        setLogEnabled(true)
        setHTTPSRequestEnabled(true)
    }
}

class HttpDns : Dns {
    override fun lookup(hostname: String): MutableList<InetAddress> {
        val ip = httpDns.getIpByHostAsync(hostname)
        return if (ip.isNullOrEmpty()) {
            Dns.SYSTEM.lookup(hostname)
        } else {
            arrayListOf(InetAddress.getByName(ip))
        }
    }

}