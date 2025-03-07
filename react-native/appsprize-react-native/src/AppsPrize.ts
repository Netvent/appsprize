import { AppsPrizeEventType, type AppsPrizeListener, type OnInitializeEvent, type OnInitializeFailedEvent, type OnNotificationEvent, type OnRewardUpdateEvent } from "./Events"
import AppsPrizeNative, { addEventListener, decodeData, encodeData, removeAllListeners } from "./AppsPrizeNative"
import type { AppRewards, AppsPrizeConfig, AppsPrizeOptions } from "./Data";


function init(config: AppsPrizeConfig, listener: AppsPrizeListener) {
    removeAllListeners();
    let rawConfig = encodeData({ config });
    if (!rawConfig) return;

    addEventListener(AppsPrizeEventType.onInitialize, (nativeEvent) => {
        let event = decodeData(nativeEvent.raw) as OnInitializeEvent
        if (listener.onInitialize) listener.onInitialize(event) 
    })
    addEventListener(AppsPrizeEventType.onInitializeFailed, (nativeEvent) => {
        let event = decodeData(nativeEvent.raw) as OnInitializeFailedEvent
        if (listener.onInitializeFailed) listener.onInitializeFailed(event) 
    })
    addEventListener(AppsPrizeEventType.onRewardUpdate, (nativeEvent) => {
        let event = decodeData(nativeEvent.raw) as OnRewardUpdateEvent
        if (listener.onRewardUpdate) listener.onRewardUpdate(event) 
    })
    addEventListener(AppsPrizeEventType.onNotification, (nativeEvent) => {
        let event = decodeData(nativeEvent.raw) as OnNotificationEvent
        if (listener.onNotification) listener.onNotification(event)
    })
    AppsPrizeNative?.init(rawConfig)
}

function launch(options?: AppsPrizeOptions): Promise<boolean> {
    let rawOptions = encodeData({ options }) ?? "";
    return AppsPrizeNative?.launch(rawOptions) ?? Promise.reject("AppsPrizeNative not available")
}

function open(campaignId: number): Promise<boolean> {
    return AppsPrizeNative?.open(campaignId) ?? Promise.reject("AppsPrizeNative not available")
}

function doReward(callback: (rewards: AppRewards[])=>void) {
    AppsPrizeNative?.doReward( raw => {
        let data = decodeData(raw)["rewards"] as AppRewards[]
        callback(data)
    })
}

function hasPermissions(): Promise<boolean> {
    return AppsPrizeNative?.hasPermissions() ?? Promise.reject("AppsPrizeNative not available")
}

function requestPermission(): Promise<boolean> {
    return AppsPrizeNative?.requestPermission() ?? Promise.reject("AppsPrizeNative not available")
}


export default {
    init,
    launch,
    open,
    doReward,
    hasPermissions,
    requestPermission,
};
