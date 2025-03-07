using UnityEngine;


namespace AppsPrizeUnity.Platforms.Android
{
    internal static class AppsPrizeOfferwallOptionsAndroid
    {
        public static AndroidJavaObject Create(AppsPrizeOfferwallOptions config)
        {
            AndroidJavaObject optionsBuilder = new("com.appsamurai.appsprize.config.AppsPrizeOfferwallOptions$Builder");

            if (config.type.HasValue)
            {
                var type = CreateType(config.type.Value);
                Debug.Log(type);
                optionsBuilder.Call<AndroidJavaObject>("setType", type);
            }
            AndroidJavaObject appsPrizeOption = optionsBuilder.Call<AndroidJavaObject>("build");
            return appsPrizeOption;
        }

        public static AndroidJavaObject CreateType(AppsPrizeOfferwallOptionType type)
        {
            AndroidJavaClass enumType = new("com.appsamurai.appsprize.config.AppsPrizeOfferwallType");
            string typeString = type switch
            {
                AppsPrizeOfferwallOptionType.ONLY_TIME => "ONLY_TIME",
                AppsPrizeOfferwallOptionType.ONLY_TASK => "ONLY_TASK",
                _ => "ALL",
            };
            return enumType.GetStatic<AndroidJavaObject>(typeString);
        }
    }
}
