## RELEASE 1.1 NOTE ##

### OSGI ###
> added method lookupBeanManager() in org.bsc.bean.osgi.BeanManagerService
> added method regiaterBeanManagerEx() in org.bsc.bean.osgi.BeanManagerService to register a beanManager using a custom key
> added org.bsc.bean.osgi.BeanManagerServiceTracker as OSGI ServiceTracker

### FIX ###
> CharBooleanAdapter didn't work with strings' length greater than 1

### BEANINFO ###
> Added 'required' properties to PropertyDescriptorField (useful to DdlUtils integration)

### BEANMANAGER ###
> Added 'CLOBAdapter'
> Added 'storeAll' method to update all rows using bean's properties
> added 'findAndRemove' method to remove rows using filter

### IMPROVEMENTS ###
> Change StringBuffer to StringBuilder when possible
> Use var args instead of Object array when possible
