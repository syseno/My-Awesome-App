package com.example.myawesomeapp.tools

sealed class ProgressState {
    class DONE(val doneMessage: String? = null) : ProgressState()
    class LOADING : ProgressState()
    class ERROR(errorMessage: String) : ProgressState()
    class INITIAL(val initialMessage: String? = null) : ProgressState()
    class EMPTY() : ProgressState()
}