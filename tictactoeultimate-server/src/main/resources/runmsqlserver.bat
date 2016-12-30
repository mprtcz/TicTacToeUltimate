for /F "tokens=3 delims=: " %%H in ('sc query "mysql57" ^| findstr "        STATE"') do (
  if /I "%%H" NEQ "RUNNING" (
   net start "mysql57"
  )
)