Global CurrentXMLFilename$


Function SerializeHashmap(hashmap%, filename$)
    node = SerializeHashmapRecursive(hashmap%)
    xmlSave(filename$, node)
    xmlNodeDelete(node)
End Function 


Function SerializeHashmapRecursive(hashmap%, parent% = 0, rootName$ = "root")
    root = xmlNodeAdd(parent%, rootName$)
    eached% = EachHashmap(hashmap%)
    CurrentKey$ = GiveKey(eached%)

	While Not CurrentKey$ = NO_KEY_DEFINED
		CurrentValue$ = ReadKey(hashmap%, CurrentKey$)

        If IsHashmap(Int(CurrentValue$))
            SerializeHashmapRecursive(Int(CurrentValue$), root, CurrentKey$)
        Else
            xmlNodeAttributeValueSet(root, CurrentKey$, CurrentValue$)
        EndIf

		CurrentKey$ = GiveKey(eached%)
	Wend
    
    Return root
End Function


Function DeserializeHashmap(filename$)
    rootnode = xmlLoad(filename$)
    hashmap = DeserializeHashmapRecursive(rootnode)

    Return hashmap
End Function

Function DeserializeHashmapRecursive(node)
    hashmap = CreateHashmap()

    ;Attibutes cycle
    this.xmlNode = Object.xmlNode(node)
    For i = 1 To this\AttributeCount
        WriteKey(hashmap, this\AttributeName[i], this\AttributeValue[i])
    Next

    ;Childs cycle
    count = xmlNodeChildCount(node)
    For i = 1 To count 
        childnode = xmlNodeChild(node, i)
        WriteKey(hashmap, xmlNodeNameGet(childnode), DeserializeHashmapRecursive(childnode))
    Next

    Return hashmap
End Function 