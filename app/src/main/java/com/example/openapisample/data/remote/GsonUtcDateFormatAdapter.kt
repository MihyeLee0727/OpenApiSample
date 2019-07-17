package com.example.openapisample.data.remote

import com.google.gson.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class GsonUtcDateFormatAdapter : JsonSerializer<Date>, JsonDeserializer<Date> {
    private val format = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US)

    override fun serialize(
        src: Date?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(format.format(src))
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date {
        return format.parse(json?.asString)
    }

}