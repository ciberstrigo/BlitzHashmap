Include "../xmlSerialize.bb"
Include "../hashmap.bb"
Include "../BlitzXML.bb"

Function AssertHashmapCreatable()
    hashmap$ = CreateHashmap()

    If IsHashmap(hashmap)
        Return True
    EndIf

    Return False
End Function 

Function AssertHashmapWriteAndRead()
    FIRSTNAME_KEY = "Firstname"
    FIRSTNAME_VALUE = "Leonid"

    LASTNAME_KEY = "Lastname"
    LASTNAME_VALUE = "Averyanov"

    hashmap$ = CreateHashmap()
    WriteKey(hashmap, FIRSTNAME_KEY, FIRSTNAME_VALUE)
    WriteKey(hashmap, LASTNAME_KEY, LASTNAME_VALUE)

    READED_FIRSTNAME_VALUE = ReadKey(hashmap, FIRSTNAME_KEY)
    READED_LASTNAME_VALUE = ReadKey(hashmap, LASTNAME_KEY)

    If (FIRSTNAME_VALUE = READED_FIRSTNAME_VALUE And LASTNAME_VALUE = READED_LASTNAME_VALUE)
        Return True
    EndIf

    Return False
End Function 

Function AssertHashmapClone()
    FIRSTNAME_KEY = "Firstname"
    FIRSTNAME_VALUE = "Leonid"

    LASTNAME_KEY = "Lastname"
    LASTNAME_VALUE = "Averyanov"

    hashmap$ = CreateHashmap()
    WriteKey(hashmap, FIRSTNAME_KEY, FIRSTNAME_VALUE)
    WriteKey(hashmap, LASTNAME_KEY, LASTNAME_VALUE)

    clonedHashmap$ = CloneHashmap(hashmap)

    READED_FIRSTNAME_VALUE = ReadKey(clonedHashmap, FIRSTNAME_KEY)
    READED_LASTNAME_VALUE = ReadKey(clonedHashmap, LASTNAME_KEY)

    If (FIRSTNAME_VALUE = READED_FIRSTNAME_VALUE And LASTNAME_VALUE = READED_LASTNAME_VALUE)
        Return True
    EndIf

    Return False
End Function 

Function AssertHashmapExtends()
    ORIGINAL_FIRSTNAME_KEY = "Firstname"
    ORIGINAL_FIRSTNAME_VALUE = "Leonid"

    ORIGINAL_LASTNAME_KEY = "Lastname"
    ORIGINAL_LASTNAME_VALUE = "Averyanov"

    original$ = CreateHashmap()
    WriteKey(original, ORIGINAL_FIRSTNAME_KEY, ORIGINAL_FIRSTNAME_VALUE)
    WriteKey(original, ORIGINAL_LASTNAME_KEY, ORIGINAL_LASTNAME_VALUE)


    EXTENDEDBY_EDUCATION_KEY = "Education"
    EXTENDEDBY_EDUCATION_VALUE = "Polytech college #8"
    
    EXTENDEDBY_WORK_KEY = "Work"
    EXTENDEDBY_WORK_VALUE = "Reforma IT"

    extendedBy$ = CreateHashmap()
    WriteKey(extendedBy, EXTENDEDBY_EDUCATION_KEY, EXTENDEDBY_EDUCATION_VALUE)
    WriteKey(extendedBy, EXTENDEDBY_WORK_KEY, EXTENDEDBY_WORK_VALUE)
    
    ExtendHashmap(original, extendedBy)
    
    If (Not(ReadKey(original, EXTENDEDBY_EDUCATION_KEY) = ReadKey(extendedBy, EXTENDEDBY_EDUCATION_KEY)))
        Return False
    EndIf
    
    If (Not(ReadKey(original, EXTENDEDBY_WORK_KEY) = ReadKey(extendedBy, EXTENDEDBY_WORK_KEY)))
        Return False
    EndIf
    
    If (Not(ReadKey(original, ORIGINAL_FIRSTNAME_KEY) = ORIGINAL_FIRSTNAME_VALUE))
        Return False
    EndIf
    
    If (Not(ReadKey(original, ORIGINAL_LASTNAME_KEY) = ORIGINAL_LASTNAME_VALUE))
        Return False
    EndIf
    
    Return True
End Function 

Function AssertDeleteKey()
    FIRSTNAME_KEY = "Firstname"
    FIRSTNAME_VALUE = "Leonid"

    LASTNAME_KEY = "Lastname"
    LASTNAME_VALUE = "Averyanov"

    hashmap$ = CreateHashmap()
    WriteKey(hashmap, FIRSTNAME_KEY, FIRSTNAME_VALUE)
    WriteKey(hashmap, LASTNAME_KEY, LASTNAME_VALUE)

    DeleteKey(hashmap, LASTNAME_KEY)

    If (ReadKey(hashmap, LASTNAME_KEY) = NO_KEY_DEFINED$)
        Return True
    EndIf 

    Return False
End Function 

Function AssertDeleteHashmap()
    FIRSTNAME_KEY = "Firstname"
    FIRSTNAME_VALUE = "Leonid"

    LASTNAME_KEY = "Lastname"
    LASTNAME_VALUE = "Averyanov"

    hashmap$ = CreateHashmap()
    WriteKey(hashmap, FIRSTNAME_KEY, FIRSTNAME_VALUE)
    WriteKey(hashmap, LASTNAME_KEY, LASTNAME_VALUE)

    DeleteHashmap(hashmap)

    Return Not (IsHashmap(hashmap))
End Function

Function AssertEachingHashmapStartingFromOne()
    hashmap$ = CreateHashmap()
    WriteKey(hashmap, 0, "surrrender")
    WriteKey(hashmap, 1, "is")
    WriteKey(hashmap, 9, "not")
    WriteKey(hashmap, 2, "an")
    WriteKey(hashmap, 8, "options")
    WriteKey(hashmap, 3, "!!!")

    eaching% = EachHashmap$(hashmap$)
    CurrentKey$ = GiveKey(eaching%)
    counter% = 1

    While Not(CurrentKey$ = NO_KEY_DEFINED$)
        DebugLog(CurrentKey$)
        If counter% = 2 And Int(CurrentKey$) = 2
            If counter% = Int(CurrentKey$)
                If "an" = ReadKey(hashmap, CurrentKey$)
                    Return True
                EndIf
            EndIf
        EndIf
        CurrentKey$ = GiveKey(eaching%)
        counter% = counter% + 1
    Wend

    Return False
End Function 

Function Assert()
    Local IsAllPassed = True

    If (Not AssertHashmapCreatable())
        DebugLog("AssertHashmapCreatable() was not passed!")
        IsAllPassed = False
    Else 
        DebugLog("AssertHashmapCreatable() passed!")
    EndIf

    If (Not AssertHashmapWriteAndRead())
        DebugLog("AssertHashmapWriteAndRead() was not passed!")
        IsAllPassed = False
    Else
        DebugLog("AssertHashmapWriteAndRead() passed!")
    EndIf

    If (Not AssertHashmapClone())
        DebugLog("AssertHashmapClone() was not passed!")
        IsAllPassed = False
    Else
        DebugLog("AssertHashmapClone() passed!")
    EndIf

    If (Not AssertHashmapExtends())
        DebugLog("AssertHashmapExtends() was not passed!")
        IsAllPassed = False
    Else
        DebugLog("AssertHashmapExtends() passed!")
    EndIf

    If (Not AssertDeleteKey())
        DebugLog("AssertDeleteKey() was not passed!")
        IsAllPassed = False
    Else
        DebugLog("AssertDeleteKey() passed!")
    EndIf

    If (Not AssertDeleteHashmap())
        DebugLog("AssertDeleteHashmap() was not passed!")
        IsAllPassed = False
    Else
        DebugLog("AssertDeleteHashmap() passed!")
    EndIf

    If (Not AssertEachingHashmapStartingFromOne())
        DebugLog("AssertEachingHashmapStartingFromOne() was not passed!")
        IsAllPassed = False
    Else
        DebugLog("AssertEachingHashmapStartingFromOne() passed!")
    EndIf

    If IsAllPassed
        DebugLog("All tests passed!")
        ClsColor(0, 255, 0)
    Else
        ClsColor(255, 0, 0)
    EndIf
    
    Cls()
    
    Stop()

End Function 

Assert()