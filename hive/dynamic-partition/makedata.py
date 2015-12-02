from datetime import date
from datetime import timedelta

recordsPerDay=10
daysInYear=365
numRecords=recordsPerDay * daysInYear

start=date(2014,1,1)
with open("data.txt", "w") as f:
    for club in ['ca', 'oh', 'tx']:
        for i in range(0, numRecords):
            bucket=i % daysInYear
            recDate=start + timedelta(days=bucket)
            f.write("{0},{1:02d}{2:02d}{3:02d},{4}\n".format(club, recDate.year, recDate.month, recDate.day, i+1))

