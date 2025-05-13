def test(): 
    while True: 
        yield 1

g = test()
print(next(g))
print(type(g), type(test))