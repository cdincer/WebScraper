UnitHtml Webscraper for Eurobonds

Installation Notes:
Created and tested on Eclipse/Windows installation with a MySQL database.
After you get those you head to persistence.xml and change it to your connection settings.
Next up is looking for the line below,in persistence.xml for running it first time. 
You change value bit to create after that you use update.
<property name="hibernate.hbm2ddl.auto" value="update" />
