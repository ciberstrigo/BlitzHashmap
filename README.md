# BlitzHashmap
Blitz3d hashmap library, created as simple as it possible

## Help
### CreateHashmap()
#### Parameters: none
Return a handle of a just created empty hashmap.
### WriteKey(hashmap, key$, value$)
#### Parameters: 
##### hashmap - a handle of a hashmap
##### key$ - some string value by which the value will be available
##### value$ - some recorded value as a string
Write a value to a hashmap and make it accessible by a string key. 
Key and value are strings for the reason that string is the most versatile (polymorphic) data type. You can get an integer `Int(value$)`, a floating point `Float(value$)`, and even an object `Object.type(Int(value$))` from a string.
### ReadKey$(hashmap, key$)
#### Parameters: 
##### hashmap - a handle of a hashmap
##### key$ - some string value by which the value is available
Return a value getted from key from selected hashmap, or a `NO_KEY_DEFINED` constant value if key was not defined or deleted.
### EachHashmap(hashmap)
#### Parameters: 
##### hashmap - a handle of a hashmap
Preparing to iterate through hashmaps by keys sequentially. Return a first key of hashmap. 
### GiveKey$()
#### Parameters: none
Return the next key of hashmap of `NO_KEY_DEFINED` constant value if all keys was listed.

Example:

```
Function CloneHashmap%(original%)
	clone% = CreateHashmap()
	
	CurrentKey$ = EachHashmap(original%)
	
	While Not CurrentKey$ = NO_KEY_DEFINED
		WriteKey(clone%, CurrentKey$, ReadKey(original%, CurrentKey$))
		CurrentKey$ = GiveKey()
	Wend
	
	Return clone%
End Function 
```

### CloneHashmap(original)
#### Parameters: 
##### original - a handle of a original hashmap than will be cloned
Return a new identical (by key => value relation) hashmap.

### ExtendHashmap(original, extendedBy)
#### Parameters: 
##### original - a handle of a extendable hashmap 
##### extendedBy - a handle of a exteded by hashmap
Copies non-duplicate keys (and their values) from extendedBy to original.