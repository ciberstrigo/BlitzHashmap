Include "hashmap.bb"

Const MAX_INT = 1294967295
Function Uuid$()		
	Return Right(Hex(Rand(0, MAX_INT)), 8) + "-" + Left(Hex(Rand(0, MAX_INT)), 4) + "-" + Left(Hex(Rand(0, MAX_INT)), 4) + "-" + Left(Hex(Rand(0, MAX_INT)), 4) + "-" + Right(Hex(Rand(0, MAX_INT)), 8) + Right(Hex(Rand(0, MAX_INT)), 4)
End Function


Graphics 1280, 720, 32, 2
SetFont LoadFont("Arial", 12)
ClsColor(48, 10, 36)
Cls()



userPrototype = CreateHashmap()
WriteKey(userPrototype, "Firstname", "")
WriteKey(userPrototype, "Lastname", "")
WriteKey(userPrototype, "Age", "")
WriteKey(userPro3totype, "Gender", "")
WriteKey(userPrototype, "Id", "")

Stop()

firstUser = CloneHashmap(userPrototype)
WriteKey(firstUser, "Firstname", "Leonid")
WriteKey(firstUser, "Lastname", "Averianov")


DumpHashmap(firstUser)