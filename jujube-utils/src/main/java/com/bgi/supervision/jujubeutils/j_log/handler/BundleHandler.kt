package com.bgi.supervision.jujubelog.log.handler

import android.os.Bundle
import com.alibaba.fastjson.JSON
import com.bgi.supervision.jujubelog.handler.BaseHandler
import com.bgi.supervision.jujubelog.parser.Parser
import com.bgi.supervision.jujubeutils.Utils
import com.bgi.supervision.jujubeutils.j_log.L
import com.bgi.supervision.jujubeutils.j_log.LoggerPrinter
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by tony on 2017/11/27.
 */
class BundleHandler: BaseHandler(), Parser<Bundle> {

    override fun handle(obj: Any): Boolean {

        if (obj is Bundle) {

            val s = L.getMethodNames()
            println(String.format(s, parseString(obj)))
            return true
        }

        return false
    }

    override fun parseString(bundle: Bundle): String {

        var msg = bundle.javaClass.toString() + LoggerPrinter.BR + "║ "

        val jsonObject = JSONObject()
        for (key in bundle.keySet()) {

            val isPrimitiveType = Utils.isPrimitiveType(bundle.get(key))

            try {

                if (isPrimitiveType) {
                    jsonObject.put(key.toString(), bundle.get(key))
                } else {
                    jsonObject.put(key.toString(), JSONObject(JSON.toJSONString(bundle.get(key))))
                }
            } catch (e: JSONException) {
                L.e("Invalid Json")
            }

        }

        var message = jsonObject.toString(LoggerPrinter.JSON_INDENT)
        message = message.replace("\n".toRegex(), "\n║ ")

        return msg + message
    }
}