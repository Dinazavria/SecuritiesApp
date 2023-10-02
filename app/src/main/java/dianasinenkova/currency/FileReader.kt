package dianasinenkova.currency
import android.app.Activity
import android.content.res.Resources
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

fun dataFileReader(activity: Activity): MutableList<SecuritiesLocalDataModel> {
    val res: Resources = activity.resources
    val file: InputStream = res.openRawResource(R.raw.data)
    val text: String
    try{
        text = convertStreamToString(file)
        val lines = parseString(text)
        val list = createModelArray(lines)
        return list
//        Log.println(Log.DEBUG, "DATA FROM FILE", line[1])
    } catch(e: IOException) {
        e.printStackTrace()
        return mutableListOf()
    }
}

fun convertStreamToString(stream: InputStream): String {
    val baos = ByteArrayOutputStream()
    var i = stream.read()
    while (i != -1) {
        baos.write(i)
        i = stream.read()
    }
    return baos.toString()
}

fun parseString(str: String): Array<String> {
    val lines = str.split("\r?\n|\r".toRegex()).toTypedArray()
    return lines
}

fun createModelArray(strs: Array<String>): MutableList<SecuritiesLocalDataModel> {
    var modelArray: MutableList<SecuritiesLocalDataModel> = mutableListOf()
    for(str in strs) {
        val items = str.split(",").toTypedArray()
        if(items.size >= 3) {
        modelArray.add(
        SecuritiesLocalDataModel(
                symbol = items[0],
                name = items[1],
                isActive = items[items.size-1] == "Active"
            )
            ) }
        }
    return modelArray
}
