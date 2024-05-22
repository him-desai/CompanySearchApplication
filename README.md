- I've developed an application that interacts with the Company API followed by the Officer API.
- (Bonus Task) Upon acquiring data and meeting certain conditions, it will be stored in the database.
- To filter company status, please provide: "companyStatus":"active" or any desired status for filtering.
- The application will also retrieve corresponding officers whose Resign_On field is Null.
- While coding standards have been adhered to, intentional variations exist in certain variable names, etc. (These will be updated in the future; mentioned here for awareness.)
- Similarly, basic exception handling and validation have been implemented, with room for improvement planned for future iterations.
- H2 DB is utilized with a file-based path for data retention, which should be modified as needed for usage.
- Enabling Info Level logging can assist in checking the basic flow of the application.
-The basic search rules remain consistent with the provided task: if both the Company Number and Name are provided, only the Company Number will be utilized.

Additionally, it's important to note that exceptions for officers are not caught universally, as for certain companies, while the Company API functions properly, the Officer API may encounter errors (e.g., No: OE030985).

Please be aware that I'll use the API Key from the property file when making calls. 
