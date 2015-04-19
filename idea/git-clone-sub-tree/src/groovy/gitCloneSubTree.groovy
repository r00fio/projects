#!/usr/bin/env groovy
def init = "git init".execute()
init.waitFor()
init.text.eachLine {println it}

def add = "git remote add origin " + args[0]
println add
add.execute().waitFor()

"git config core.sparsecheckout true".execute().waitFor()
new File(".git/info/sparse-checkout").write(args[1])
"git pull --depth=1 origin master".execute().waitFor()



