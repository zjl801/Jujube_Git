package com.bgi.supervision.jujubelog.handler

import com.alibaba.fastjson.JSON
import com.bgi.supervision.jujubelog.parser.Parser
import com.bgi.supervision.jujubeutils.Utils
import com.bgi.supervision.jujubeutils.j_log.L
import com.bgi.supervision.jujubeutils.j_log.LoggerPrinter
import org.json.JSONObject
import java.lang.ref.Reference

/**
 * Created by tony on 2017/11/27.
 */
class ReferenceHandler: BaseHandler(), Parser<Reference<*>> {

    override fun handle(obj: Any): Boolean {

        if (obj is Reference<*>) {
            val s = L.getMethodNames()
            println(String.format(s, parseString(obj)))
            return true
        }

        return false
    }

    override fun parseString(reference: Reference<*>): String {
        val actual = reference.get()

        var msg = reference.javaClass.canonicalName + "<" + actual?.javaClass?.simpleName + ">"+ LoggerPrinter.BR + "║ "

        val isPrimitiveType = Utils.isPrimitiveType(actual)

        if (isPrimitiveType) {

            msg += "{" + actual + "}"
        } else {

            val jsonObject = JSONObject(JSON.toJSONString(actual))
            var message = jsonObject.toString(LoggerPrinter.JSON_INDENT)
            message = message.replace("\n".toRegex(), "\n║ ")
            msg += message
        }

        return msg
    }
}