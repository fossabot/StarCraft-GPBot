#powershell timeeeeeeeeee
#i need a script
#i can do it in java
#howeeeever
#my inside admin screams
#i've been a w$ admin for so long
#still vb sucks dicks

param($File, $classFile)
#$File is mystupidcsv.csv and $classFile is mylamenode.txt


#calm down, scv are from starcraft, csv from powershell -_-

$csv = import-csv $File

#now, let's replace
#but, first, import the whole text file

Foreach($row in $csv){

    $nameOfYourClass = $row.ClassName
    $name = $row.Name
    $children = $row.Children
    $unitType = $row.UnitType
    $newFileName = $nameOfYourClass + ".java"
    $wholeText= Get-Content $classFile
    #let's think this works
    $wholeText.replace('$nameOfYourClass', $nameOfYourClass) | $wholeText.replace('$name', $name) | | $wholeText.replace('$children', $children) | | $wholeText.replace('$unitType', $unitType) | Set-Content $newFileName #magic or stackoverflow
    #one more thing
    #well, that'll be for tomorrow
    
    
    }