with open("sqoop-action.txt", "w") as f:
    for x in range(1,100001):
        # 10 partitions
        partId=x % 10
        f.write("{0},value-{0},{1}\n".format(x, partId))


