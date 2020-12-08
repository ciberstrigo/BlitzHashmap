Global CurrentXMLFilename$


Function SerializeHashmap(hashmap%, filename$)
    root = xmlNodeAdd(-1, "root")
    SerializeHashmapRecursive(root, "root", hashmap%)
    xmlSave(filename$, root)
End Function 


Function SerializeHashmapRecursive(parent%, rootName$ = "root", hashmap%)
    eached% = EachHashmap(hashmap%)
    CurrentKey$ = GiveKey(eached%)
	While Not CurrentKey$ = NO_KEY_DEFINED
		CurrentValue$ = ReadKey(original%, CurrentKey$)

        If IsHashmap(Int(CurrentValue$))
            SerializeHashmapRecursive(parent, CurrentKey$, Int(CurrentValue$))
        Else
            xmlNodeAttributeValueSet(parent, CurrentKey$, CurrentValue$)
        EndIf

		CurrentKey$ = GiveKey(eached%)
	Wend
End Function


Function DeserializeHashmap(filename$)

End Function