using UnityEngine;


namespace AppsPrizeUnity
{
    public class AppsPrizeOfferwallOptions
    {
        public readonly AppsPrizeOfferwallOptionType? type;

        public AppsPrizeOfferwallOptions(
            AppsPrizeOfferwallOptionType? type = null
        ) {
            this.type = type;
        }
    }

    public enum AppsPrizeOfferwallOptionType
    {
        ONLY_TIME,
        ONLY_TASK,
        ALL,
    }
}