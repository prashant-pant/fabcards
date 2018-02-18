"use strict";
(function(appName){
	var app = angular.module(appName);
	var apiUrl = "http://localhost:8080/fabcards/api/";
	app.constant('AppConstants',{
		LOGIN_URL : apiUrl + "user/login",
		GET_MY_PROFILE_URL : apiUrl + "user/getMyProfile",
		REGISTER_URL : apiUrl + "user/register",
		ACCOUNT_EXISTS_URL : apiUrl+"user/accountExists",
		PROFILE_UPDATE_URL : apiUrl+"user/updateProfile",
		GET_FABCARD_URL : apiUrl+"fabcard/getFabCard",
		CREATE_FABCARD_URL : apiUrl+"fabcard/create",
		RECHARGE_FABCARD_URL : apiUrl+"fabcard/recharge",
		GET_ROOM_PRICE_URL : apiUrl+"booking/getPrice",
		BOOK_ROOM_URL : apiUrl+"booking/book",
		states : [
			      "Andhra Pradesh",
			    	"Arunachal Pradesh",
			    	"Assam",
			    	"Bihar",
			    	"Chhattisgarh",
			    	"Chandigarh",
			    	"Dadra and Nagar Haveli",
			    	"Daman and Diu",
			    	"Delhi",
			    	"Goa",
			    	"Gujarat",
			    	"Haryana",
			    	"Himachal Pradesh",
			    	"Jammu and Kashmir",
			    	"Jharkhand",
			    	"Karnataka",
			    	"Kerala",
			    	"Madhya Pradesh",
			    	"Maharashtra",
			    	"Manipur",
			    	"Meghalaya",
			    	"Mizoram",
			    	"Nagaland",
			    	"Orissa",
			    	"Punjab",
			    	"Pondicherry",
			    	"Rajasthan",
			    	"Sikkim",
			    	"Tamil Nadu",
			    	"Tripura",
			    	"Uttar Pradesh",
			    	"Uttarakhand",
			    	"West Bengal"
			    ]
	});
})("fabcards");
