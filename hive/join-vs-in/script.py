from datetime import date
from datetime import timedelta

start=date(2015,1,1)
with open("main-table.txt", "w") as f:
    for x in range(0,100000):
        joinId=x % 365
        recDate=start + timedelta(days=joinId)
        # f.write("{0},{1:02d}-{2:02d}-{3:02d}\n".format(joinId, recDate.year, recDate.month, recDate.day))
        f.write("{0},{1}\n".format(x, joinId))

with open("join-table.txt", "w") as f:
    for x in range(0, 365):
        recDate=start + timedelta(days=x)
        f.write("{0:02d}-{1:02d}-{2:02d},{3},{4}\n".format(recDate.year, recDate.month, recDate.day, "some metadata", x))

