with open("data.txt", "w") as f:
    for x in range(1,10000000):
        year=(x % 15) + 2001
        month=(x % 12) + 1
        day=(x % 30) + 1
        f.write("{0},{1:02d}-{2:02d}-{3:02d}\n".format(x, year, month, day))
