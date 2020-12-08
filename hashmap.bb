Const hashmap_size = 120

Const NO_KEY_DEFINED$ = "KEY IS NOT DEFINED"


Type hashmap
	Field keys$[hashmap_size]
	Field table.hashmap_element[hashmap_size]
	Field count%
End Type

Type hashmap_element
	Field key$
	Field value$
	Field following.hashmap_element
End Type

Function str_to_num%(s$)
	If (s$ = "")
		Return 0
	EndIf
	If (Int(s$) > 0)
		Return Int(s$) Mod hashmap_size
	Else
		Return Asc(s$) Mod hashmap_size
	EndIf
End Function

Function CreateHashmap()
	Return Handle(New hashmap)
End Function


Function WriteKey(hashmap%, key$, value$)
	this.hashmap = Object.hashmap(hashmap%)

	If (Null = this) 
		DebugLog("Hashmap is not exist!")
		Stop()
		RuntimeError("Hashmap is not exist!") 
	EndIf

	index% = str_to_num%(key$)
	element.hashmap_element = this\table[index%]
	previousElement.hashmap_element = Null
	While True
		If Null = element
			element.hashmap_element = New hashmap_element
			element\key$ = key$
			element\value$ = value$
			
			this\count% = this\count% + 1
			this\keys$[this\count%] = key$
			
			If this\table[index%] = Null
				this\table.hashmap_element[index%] = element.hashmap_element
			EndIf
			If previousElement <> Null
				previousElement\following.hashmap_element = element.hashmap_element
			EndIf	
			Return
		EndIf 
		If element\key$ = key$
			element\value$ = value$
			Return
		EndIf
		previousElement.hashmap_element = element.hashmap_element
		element.hashmap_element = element.hashmap_element\following.hashmap_element
	Wend
End Function


Function ReadKey$(hashmap%, key$)
	this.hashmap = Object.hashmap(hashmap%)

	If (Null = this) 
		DebugLog("Hashmap is not exist!")
		Stop()
		RuntimeError("Hashmap is not exist!") 
	EndIf

	index% = str_to_num%(key$)
	element.hashmap_element = this\table[index%]
	While True
		If Null = element
			Return NO_KEY_DEFINED$
			Exit
		EndIf
		If element\key$ = key$
			Return element\value$
		EndIf 
		element.hashmap_element = element.hashmap_element\following.hashmap_element
	Wend
End Function


Function DumpHashmap(hashmap%)
	this.hashmap = Object.hashmap(hashmap%)

	If (Null = this) 
		DebugLog("Hashmap is not exist!")
		Stop()
		RuntimeError("Hashmap is not exist!") 
	EndIf

	count_of_elements% = 0
	For i = 0 To hashmap_size
		element.hashmap_element = this\table.hashmap_element[i]
		While True
			If Null = element
				Exit	
			EndIf
			
			Print(element\key$ + " => " + element\value$)

			count_of_elements% = count_of_elements% + 1
					
			element.hashmap_element = element\following.hashmap_element
		Wend	
	Next
	Print("Count: " + count_of_elements%)
End Function


Type EachHashmap
	Field CurrentEachedHashmap%
	Field CurrentEachedIndex% = 0
	Field CurrentEachedElementIndex% = 0
End Type


Function EachHashmap$(hashmap%)
	this.hashmap = Object.hashmap(hashmap%)

	If (Null = this) 
		DebugLog("Hashmap is not exist!")
		Stop()
		RuntimeError("Hashmap is not exist!") 
	EndIf

	eached.EachHashmap = new EachHashmap
	eached\CurrentEachedHashmap% = hashmap%
	Return Handle(eached)
End Function 


Function GiveKey$(e%)
	eached.EachHashmap = Object.EachHashmap(e%)

	If Null = eached 
		DebugLog("Eached hashmap was not set")
		Stop()
		RuntimeError("Eached hashmap was not set")
	EndIf

	this.hashmap = Object.hashmap(eached\CurrentEachedHashmap%)
	
	If Null = this
		DebugLog("Eached hashmap was not set")
		Stop()
		RuntimeError("Eached hashmap was not set")
	EndIf

	count_of_elements% = 0
	
	For i = eached\CurrentEachedIndex% To hashmap_size
		element.hashmap_element = this\table.hashmap_element[i]
		counter_index% = 0
		
		While True
		
			If Null = element
				eached\CurrentEachedElementIndex% = 0
				Exit	
			EndIf
		
			counter_index% = counter_index% + 1

			If (counter_index% > eached\CurrentEachedElementIndex%) 
				eached\CurrentEachedElementIndex% = counter_index%
				eached\CurrentEachedIndex% = i

				Return element\key$
			EndIf
			
			element.hashmap_element = element\following.hashmap_element
			
		Wend
	Next
	
	eached\CurrentEachedIndex% = 0
	eached\CurrentEachedElementIndex% = 0
	eached\CurrentEachedHashmap% = 0
	
	Delete eached

	Return NO_KEY_DEFINED
End Function 

Function CloneHashmap%(original%)
	hashmapEach% = EachHashmap(original%)
	CurrentKey$ = GiveKey(hashmapEach%)

	clone% = CreateHashmap()
	
	While Not CurrentKey$ = NO_KEY_DEFINED
		WriteKey(clone%, CurrentKey$, ReadKey(original%, CurrentKey$))
		CurrentKey$ = GiveKey(hashmapEach%)
	Wend
	
	Return clone%
End Function 

Function ExtendHashmap(original%, extendedBy%)
	hashmapEach% = EachHashmap(extendedBy%)
	CurrentKey$ = GiveKey(hashmapEach%)
	
	While Not CurrentKey$ = NO_KEY_DEFINED
		If (ReadKey(original%, CurrentKey$) = NO_KEY_DEFINED)
			WriteKey(original%, CurrentKey$, ReadKey(extendedBy%, CurrentKey$))
		EndIf
		CurrentKey$ = GiveKey(hashmapEach%)
	Wend
	
	;Return clone%
End Function 

Function IsHashmap(hashmap%)
	this.hashmap = Object.hashmap(hashmap%)
	If (Null = this)
		Return False
	EndIf

	Return True
End Function


Function DeleteKey(hashmap%, key$)
	this.hashmap = Object.hashmap(hashmap%)

	If (Null = this) 
		DebugLog("Hashmap is not exist!")
		Stop()
		RuntimeError("Hashmap is not exist!") 
	EndIf

	index% = str_to_num%(key$)
	element.hashmap_element = this\table[index%]
	While True
		If Null = element
			Return NO_KEY_DEFINED$
			Exit
		EndIf
		If element\key$ = key$
			Delete element
			Exit
		EndIf 
		element.hashmap_element = element.hashmap_element\following.hashmap_element
	Wend

	Return NO_KEY_DEFINED$
End Function 


Function DeleteHashmap(hashmap%)
	this.hashmap = Object.hashmap(hashmap%)

	If (Null = this) 
		DebugLog("Hashmap is not exist!")
		Stop()
		RuntimeError("Hashmap is not exist!") 
	EndIf

	index% = str_to_num%(key$)
	element.hashmap_element = this\table[index%]
	
	While True
		If Null = element
			Exit
		EndIf
		If element\key$ = key$
			Delete element
			Exit
		EndIf 
		element.hashmap_element = element.hashmap_element\following.hashmap_element
	Wend

	Delete this
End Function