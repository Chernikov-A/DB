package com.example.dbproject.Config;

public class Const {
    // константы таблицы User для авторизации
    public static final String USER_TABLE = "user";
    public static final String USER_ID = "idUsers";
    public static final String USER_NAME = "user";
    public static final String USER_PASSWORD = "password";

    // констаты таблицы document
    public static final String DOCUMENT_TABLE = "document";
    public static final String DOCUMENT_ID = "idDocuments";
    public static final String DOCUMENT_NAME = "Name_Document";
    public static final String DOCUMENT_DESCRIPTION = "Description";
    public static final String DOCUMENT_CREATION_DATE = "date_of_creation";
    public static final String DOCUMENT_EXECUTION_DATE = "Execution_date";
    public static final String DOCUMENT_CURRENT_STATUS = "Current_status";
    public static final String DOCUMENT_PREPARED = "prepared";
    public static final String DOCUMENT_SIGNED = "Signed";
    public static final String DOCUMENT_CONTACT = "contact";
    public static final String DOCUMENT_SUM = "sum";
    public static final String DOCUMENT_TIPS = "tip_documenta";
    public static final String DOCUMENT_KONTRAGENT = "kontragent";

    // констаты таблицы product
    public static final String PRODUCT_TABLE = "product";
    public static final String PRODUCT_ID = "idproduct";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_AMOUNT = "amount";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_CATEGORY = "category";
    public static final String PRODUCT_PROVIDER = "provider";

    // константы таблицы draft_documents
    public static final String DRAFT_DOCUMENT_TABLE = "draft_documents";
    public static final String DRAFT_DOCUMENT_ID = "idDraft_Documents";
    public static final String DRAFT_DOCUMENT_NAME = "Draft_NameDocument";
    public static final String DRAFT_DOCUMENT_DESCRIPTION = "Draft_Description";
    public static final String DRAFT_DOCUMENT_CREATION_DATE = "Draft_dateOfCreation";
    public static final String DRAFT_DOCUMENT_EXECUTION_DATE = "Draft_ExecutionDate";
    public static final String DRAFT_DOCUMENT_CURRENT_STATUS = "Draft_CurrentStatus";
    public static final String DRAFT_DOCUMENT_PREPARED = "Draft_Prepared";
    public static final String DRAFT_DOCUMENT_SIGNED = "Draft_Signed";
    public static final String DRAFT_DOCUMENT_CONTACT = "Draft_Contact";
    public static final String DRAFT_DOCUMENT_SUM = "Draft_sum";
    public static final String DRAFT_DOCUMENT_TIPS = "Draft_TipDocumenta";
    public static final String DRAFT_DOCUMENT_KONTRAGENT = "Draft_Kontragent";

    // константы таблицы OfficeEquipment
    public static final String OFFICE_TABLE = "officeequipment";
    public static final String OFFICE_ID = "idOfficeEquipment";
    public static final String OFFICE_NAME = "name";
    public static final String OFFICE_AMOUNT = "amount";
    public static final String OFFICE_COUNT = "count";

    // константы таблицы Tasks
    public static final String TASKS_TABLE = "tasks";
    public static final String TASKS_ID = "idTasks";
    public static final String TASKS_NAME = "name";
    public static final String TASKS_STATUS = "status";
    public static final String TASKS_START_DATE = "start_date";
    public static final String TASKS_END_DATE = "end_date";
    public static final String TASKS_DESCRIPTION = "description";

    // константы таблицы draft_task
    public static final String DRAFT_TASKS_TABLE = "draft_task";
    public static final String DRAFT_TASKS_ID = "iddraft_Task";
    public static final String DRAFT_TASKS_NAME = "draft_name";
    public static final String DRAFT_TASKS_STATUS = "draft_status";
    public static final String DRAFT_TASKS_START_DATE = "draft_start_date";
    public static final String DRAFT_TASKS_END_DATE = "draft_end_date";
    public static final String DRAFT_TASKS_DESCRIPTION = "draft_description";

    // константы таблицы expenses
    public static final String EXPENSES_TABLE = "expenses";
    public static final String EXPENSES_ID = "idexpenses";
    public static final String EXPENSES_NAME = "name";
    public static final String EXPENSES_AMOUNT = "amount";
    public static final String EXPENSES_COUNT = "count";

    // константы таблицы income
    public static final String INCOME_TABLE = "income";
    public static final String INCOME_ID = "idincome";
    public static final String INCOME_NAME = "name_product";
    public static final String INCOME_AMOUNT = "amount";
    public static final String INCOME_COUNT = "count";
    public static final String INCOME_BEGINNING_OF_THE_MONTH = "beginningOfTheMonth";
    public static final String INCOME_END_OF_THE_MONTH = "EndOfTheMonth";

    // константы таблицы salary
    public static final String SALARY_TABLE = "salary";
    public static final String SALARY_ID = "idsalary";
    public static final String SALARY_SOTRUDNIK = "sotrudnik";
    public static final String SALARY_AMOUNT = "amount";
    public static final String SALARY_BID = "bid";
    public static final String SALARY_JOB = "job";

    // константы таблицы process
    public static final String PROCESSING_TABLE = "process";
    public static final String PROCESSING_ID = "idProcess";
    public static final String PROCESSING_NAME = "Name_Process";
    public static final String PROCESSING_DESCRIPTION = "Description_Process";
    public static final String PROCESSING_START_DATE = "Start_Process";
    public static final String PROCESSING_END_DATE = "End_Process";
    //
}
