# -*- coding: utf-8 -*-

from os.path import join
from pathlib import Path
import pprint as pp
import gspread

cred_file = join(Path.cwd(), 'cred.json')
gc = gspread.service_account(
    filename=cred_file,
    scopes=gspread.auth.READONLY_SCOPES)

# Rush Order Spreadsheet
rush_order_id = '1V_4ot76E8RSgjZ08JiZzv0bGQzT8q0YV3cOye8C7m68'
rush_order_sh = gc.open_by_key(rush_order_id)

ro_title = rush_order_sh.title
ro_url   = rush_order_sh.url
print(ro_title, ro_url)

ro_first_ten_rows = rush_order_sh.values_get('A1:I11')
#pp.pprint(ro_first_ten_rows, indent=2)

for value in ro_first_ten_rows['values']:
    print(len(value))


# The first worksheet
sh1 = rush_order_sh.sheet1
sh1_title = sh1.title
sh1_url   = sh1.url
print(sh1_title, sh1_url)
