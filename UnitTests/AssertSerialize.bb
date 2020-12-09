Include "../xmlSerialize.bb"
Include "../hashmap.bb"
Include "../BlitzXML.bb"


Function AssertSerialize()
    hashmap = CreateHashmap()
    WriteKey(hashmap, "Firstname", "Leonid")
    WriteKey(hashmap, "Lastname", "Averianov")

    birthday = CreateHashmap()
    WriteKey(birthday, "Year", "1996")
    WriteKey(birthday, "Month", "January")
    WriteKey(birthday, "Day", "03")

    WriteKey(hashmap, "Birthday", birthday)

    SerializeHashmap(hashmap, "AssertSerialise.xml")
End Function

Function AssertDeserialize() 
	hashmap = DeserializeHashmap("AssertSerialise.xml")
	DebugLog(ReadKey(hashmap, "Firstname"))
End Function 


AssertSerialise()
AssertDeserialize() 