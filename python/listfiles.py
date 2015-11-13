import sys
import subprocess
from subprocess import Popen, PIPE

dirname = sys.argv[1]
cat = subprocess.Popen(["hdfs", "dfs", "-ls", "-R", dirname], stdout=subprocess.PIPE)
for line in cat.stdout:
    print line
