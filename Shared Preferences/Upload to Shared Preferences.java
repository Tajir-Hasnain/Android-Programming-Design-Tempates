/*
* Here TutionInfo is an object class.
* To use this function, change TutionInfo class with the required class that stores the information.
* The function fetches the prestored information in the file as a List of the mentioned class from file named "TutionManagerSharedPreferences" with getPreStoredInformationFromSharedPreference() function
* Then it overwrites the List with necessary updates,
* Then the list is updated to overwrite the file with OverwriteSharedPreference() function.
* Gson is used to convert informations from List to Json and vice versa.
*/
private void uploadToSharedPreference(TutionInfo tutionInfo) {

        ArrayList< TutionInfo > TutionList = new ArrayList<>();

        // Get prestored list from sharedPreference

        SharedPreferences sharedPreference = getSharedPreferences("TutionManagerSharedPreference" , MODE_PRIVATE);

        TutionList = getPreStoredInformationFromSharedPreference(sharedPreference);


        // Overwrite sharedPreference

        sharedPreference.edit().clear();

        TutionList.add(tutionInfo);

        OverwriteSharedPreference(sharedPreference,TutionList);


    }
    
    public ArrayList< TutionInfo > getPreStoredInformationFromSharedPreference(SharedPreferences sharedPreference) {

        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPreference.edit();

        String StoredListString = sharedPreference.getString("TutionList", null);

        java.lang.reflect.Type type = new TypeToken<ArrayList< TutionInfo > >(){}.getType();

        ArrayList < TutionInfo > PreStoredList;

        if(StoredListString != null)
            PreStoredList = gson.fromJson(StoredListString, type);

        else
            PreStoredList = new ArrayList<>();

        return  PreStoredList;
    }
    
    private void OverwriteSharedPreference(SharedPreferences sharedPreference, ArrayList < TutionInfo > information) {
        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPreference.edit();

        editor.clear();
        editor.putString("TutionList", gson.toJson(information));
        editor.apply();

    }
