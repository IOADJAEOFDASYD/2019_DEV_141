# 2019_DEV_141

## Compiling

1. Download Kotlin compiler 'kotlin-compiler-1.3.21.zip' (https://github.com/JetBrains/kotlin/releases/tag/v1.3.21).
2. Open a command-line prompt on the game's directory.
3. Run 'kotlinc src/ -include-runtime -d tictactoe.jar'
4. Run 'java -jar tictactoe.jar'
5. Play!

## Issues

If you get 'kotlinc not found' you must either replace 'kotlinc' with the full path to the 'kotlinc' executable or add the 'bin' folder of the kotlin compiler to the PATH environment variable.