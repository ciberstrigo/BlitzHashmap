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


Global CurrentEachedHashmap%
Global CurrentEachedIndex% = 0
Global CurrentEachedElementIndex% = 0


Function EachHashmap$(hashmap%)
	this.hashmap = Object.hashmap(hashmap%)

	If (Null = this) 
		DebugLog("Hashmap is not exist!")
		Stop()
		RuntimeError("Hashmap is not exist!") 
	EndIf

	CurrentEachedHashmap% = hashmap%
	Return GiveKey$()
End Function 


Function GiveKey$()
	this.hashmap = Object.hashmap(CurrentEachedHashmap%)
	
	If Null = this
		DebugLog("Eached hashmap was not set")
		Stop()
		RuntimeError("Eached hashmap was not set")
	EndIf
	
	count_of_elements% = 0
	
	For i = CurrentEachedIndex% To hashmap_size
		element.hashmap_element = this\table.hashmap_element[i]
		counter_index% = 0
		
		While True
		
			If Null = element
				CurrentEachedElementIndex% = 0
				Exit	
			EndIf
		
			
			counter_index% = counter_index% + 1

			If (counter_index% > CurrentEachedElementIndex%) 
				CurrentEachedElementIndex% = counter_index%
				CurrentEachedIndex% = i

				Return element\key$
			EndIf
			
			element.hashmap_element = element\following.hashmap_element
			
		Wend
	Next
	
	CurrentEachedIndex% = 0
	CurrentEachedElementIndex% = 0
	CurrentEachedHashmap% = 0
	
	Return NO_KEY_DEFINED
End Function 


Function CloneHashmap%(original%)
	CurrentKey$ = EachHashmap(original%)
	
	clone% = CreateHashmap()
	
	While Not CurrentKey$ = NO_KEY_DEFINED
		WriteKey(clone%, CurrentKey$, ReadKey(original%, CurrentKey$))
		CurrentKey$ = GiveKey()
	Wend
	
	Return clone%
End Function 

Function ExtendHashmap(original%, extendedBy%)
	CurrentKey$ = EachHashmap(extendedBy%)
	
	While Not CurrentKey$ = NO_KEY_DEFINED
		If (ReadKey(original%, CurrentKey$) = NO_KEY_DEFINED)
			WriteKey(original%, CurrentKey$, ReadKey(extendedBy%, CurrentKey$))
		EndIf
		CurrentKey$ = GiveKey()
	Wend
	
	Return clone%
End Function 





























