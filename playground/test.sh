OUTPUTDIR=$1
rm -r $OUTPUTDIR
mkdir $OUTPUTDIR
rm test_*.*
rm result.csv
rm -r report
$JMETER_HOME/bin/jmeter -n -t testreactive.jmx -l result.csv -e -o report
mv test_*.* $OUTPUTDIR
mv result.csv $OUTPUTDIR
mv report $OUTPUTDIR
