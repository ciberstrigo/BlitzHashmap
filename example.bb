Include "xmlSerialize.bb"
Include "hashmap.bb"
Include "BlitzXML.bb"

Const MAX_INT = 1294967295
Function Uuid$()		
	Return Right(Hex(Rand(0, MAX_INT)), 8) + "-" + Left(Hex(Rand(0, MAX_INT)), 4) + "-" + Left(Hex(Rand(0, MAX_INT)), 4) + "-" + Left(Hex(Rand(0, MAX_INT)), 4) + "-" + Right(Hex(Rand(0, MAX_INT)), 8) + Right(Hex(Rand(0, MAX_INT)), 4)
End Function


config$ = DeserializeHashmap("config.xml") 
config_graphics$ = ReadKey(config$, "graphics")

Graphics3D ReadKey(config_graphics$, "width"), ReadKey(config_graphics$, "height"), ReadKey(config_graphics$, "depth"), ReadKey(config_graphics$, "mode")
SetFont LoadFont("Arial", 12)
ClsColor(48, 10, 36)
Cls()



userPrototype$ = CreateHashmap()
WriteKey(userPrototype$, "Firstname", "")
WriteKey(userPrototype$, "Lastname", "")
WriteKey(userPrototype$, "Age", "")
WriteKey(userPrototype$, "Gender", "")
WriteKey(userPrototype$, "Id", "")


firstUser$ = CloneHashmap(userPrototype)
WriteKey(firstUser$, "Firstname", "Leonid")
WriteKey(firstUser$, "Lastname", "Averianov")


DumpHashmap(ReadKey(config$, "controls"))

While Not KeyHit(1)


	
	UpdateWorld
	RenderWorld
	Flip(ReadKey(config_graphics$, "vsync"))

Wend
End