Include "../hashmap.bb"

Function AssertHashmapCreatable()
    hashmap = CreateHashmap()

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

    hashmap = CreateHashmap()
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

    hashmap = CreateHashmap()
    WriteKey(hashmap, FIRSTNAME_KEY, FIRSTNAME_VALUE)
    WriteKey(hashmap, LASTNAME_KEY, LASTNAME_VALUE)

    clonedHashmap = CloneHashmap(hashmap)

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

    original = CreateHashmap()
    WriteKey(original, ORIGINAL_FIRSTNAME_KEY, ORIGINAL_FIRSTNAME_VALUE)
    WriteKey(original, ORIGINAL_LASTNAME_KEY, ORIGINAL_LASTNAME_VALUE)


	EXTENDEDBY_EDUCATION_KEY = "Education"
	EXTENDEDBY_EDUCATION_VALUE = "Polytech college #8"
	
	EXTENDEDBY_WORK_KEY = "Work"
	EXTENDEDBY_WORK_VALUE = "Reforma IT"

	extendedBy = CreateHashmap()
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

Function Assert()
	Local IsAllPassed = True

    If (Not AssertHashmapCreatable())
        DebugLog("AssertHashmapCreatable() was not passed!")
        IsAllPassed = False
    EndIf

    If (Not AssertHashmapWriteAndRead())
        DebugLog("AssertHashmapWriteAndRead() was not passed!")
		IsAllPassed = False
    EndIf

    If (Not AssertHashmapClone())
        DebugLog("AssertHashmapClone() was not passed!")
		IsAllPassed = False
    EndIf

	If (Not AssertHashmapExtends())
        DebugLog("AssertHashmapExtends() was not passed!")
		IsAllPassed = False
    EndIf

	If IsAllPassed
    	DebugLog("All tests passed!")
	EndIf
	
    Stop()

End Function 

Assert()