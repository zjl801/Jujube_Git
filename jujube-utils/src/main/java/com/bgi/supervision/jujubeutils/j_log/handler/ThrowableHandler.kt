package com.bgi.supervision.jujubelog.handler

import com.bgi.supervision.jujubelog.parser.Parser
import com.bgi.supervision.jujubeutils.j_log.L
import java.io.PrintWriter
import java.io.StringWriter

/**
 * Created by tony on 2017/11/27.
 */
class ThrowableHandler: BaseHandler(), Parser<Throwable> {

    override fun handle(obj: Any): Boolean {

        if (obj is Throwable) {

            val s = L.getMethodNames()
            System.err.println(String.format(s, parseString(obj)))
            return true
        }

        return false
    }

    override fun parseString(throwable: Throwable): String {
        val sw = StringWriter(256)
        val pw = PrintWriter(sw, false)
        throwable.printStackTrace(pw)
        pw.flush()
        var message = sw.toString()
        message = message.replace("\n".toRegex(), "\n║ ")

        return message
    }

}