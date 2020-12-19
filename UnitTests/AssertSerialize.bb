Include "../xmlSerialize.bb"
Include "../hashmap.bb"
Include "../BlitzXML.bb"


Function AssertSerializeAndDeserialize()
    ; Serialization part
    hashmap$ = CreateHashmap()
    WriteKey(hashmap$, "Firstname", "Leonid")
    WriteKey(hashmap$, "Lastname", "Averianov")

    birthday$ = CreateHashmap()
    WriteKey(birthday$, "Year", "1996")
    WriteKey(birthday$, "Month", "January")
    WriteKey(birthday$, "Day", "03")

    WriteKey(hashmap$, "Birthday", birthday$)

    SerializeHashmap(hashmap$, "AssertSerialise.xml")

    ; Deserialization part
    deserializedHashmap$ = DeserializeHashmap("AssertSerialise.xml")
    
    DeleteFile("AssertSerialise.xml")
    
    If Not(ReadKey(deserializedHashmap$, "Firstname") = "Leonid")     
        Return False 
    EndIf
    
    If Not(ReadKey(deserializedHashmap$, "Lastname") = "Averianov")     
        Return False 
    EndIf
    
    deserializedHashmapChild$ = Int(ReadKey(deserializedHashmap$, "Birthday"))
    
    If Not(ReadKey(deserializedHashmapChild$, "Year") = "1996")     
        Return False 
    EndIf
    
    If Not(ReadKey(deserializedHashmapChild$, "Month") = "January")     
        Return False 
    EndIf
    
    If Not(ReadKey(deserializedHashmapChild$, "Day") = "03")     
        Return False 
    EndIf
    
    Return True

End Function


Function AssertSerialize()
    Local IsAllPassed = True

    If (Not AssertSerializeAndDeserialize())
        DebugLog("AssertSerializeAndDeserialize() was not passed!")
        IsAllPassed = False
    Else 
        DebugLog("AssertSerializeAndDeserialize() passed!")
    EndIf
End Function 

AssertSerialize()