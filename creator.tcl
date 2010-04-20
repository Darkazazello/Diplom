set project_name "Simple1"
set library_name "lib1"
set file_name "example1.vhdl"
set device_family "spartan3e"
set device_name "xc3s100e"
set absolute_path "e:/diplom/generated/$file_name"
namespace import xilinx::*

project new $project_name
project set family $device_family
project set device $device_name

xfile add $absolute_path

if { [catch { time {process run "Synthesize - XST"} } synthTime]} {
    puts "Synthesis failed"
} else { 
    puts "Synthesis ran in $synthTime"
}

process run "Implement Design"