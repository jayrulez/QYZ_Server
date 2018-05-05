
f = open("gsd.xdb.xml")
lines = f.readlines()
f.close()

outs = []
id = 0
for line in lines:
	if line.find("<xbean") >= 0 or line.find("<cbean") >=0:
		id = 0
	if line.find("<variable") >= 0 and line.find("id=") < 0:
		line = line.replace("<variable", '<variable id="%s"' % id)
		id += 1
	outs.append(line)

f = open("gsd.xdb.xml", "w")
f.write("".join(outs))		