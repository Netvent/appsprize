

export interface AppsPrizeConfig {
    token: string;
    advertisingId: string;
    userId: string;
    country?: string;
    language?: string;
    gender?: string;
    age?: number;
    uaChannel?: string;
    uaNetwork?: string;
    adPlacement?: string;
    style?: AppsPrizeStyleConfig;
}

export interface AppsPrizeStyleConfig {
    primaryColor?: string;
    secondaryColor?: string;
    highlightColor?: string;
    promotionHighlightColor?: string;
    dailyHighlightColor?: string;
    cashbackHighlightColor?: string;
    secondChanceHighlightColor?: string;
    commonTaskHighlightColor?: string;
    epicTaskHighlightColor?: string;
    legendaryTaskHighlightColor?: string;
    typeface?: string;
    bannerDrawable?: string;
    offersTitleText?: string;
    appsTitleText?: string;
    currencyIcon?: string;
}

export interface AppsPrizeOptions {
    type?: "only_time" | "only_task" | "all";
}

export interface RewardLevel {
    level: number;
    points: number;
    currency: string;
}

export interface AppRewards {
    rewards: RewardLevel[];
}

export interface AppsPrizeNotification {
    id: number,
    campaignId: number,
    appName: string,
    description: string,
    hasRead: boolean,
    iconUrl?: string,
    timestamp?: number,
}