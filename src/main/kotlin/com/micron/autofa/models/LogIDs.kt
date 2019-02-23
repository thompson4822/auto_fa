package com.micron.autofa.models

data class LogIDs(val ids: List<String>)

data class LogMessage(val id: String, val log: List<String>)

data class LogMessages(val messages: List<LogMessage>)