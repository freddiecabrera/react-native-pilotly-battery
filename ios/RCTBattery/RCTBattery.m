//
//  RCTBattery.m
//  RCTBattery
//
//  Created by Freddie Cabrera on 6/1/17.
//  Copyright © 2017 Freddie Cabrera. All rights reserved.
//

//#import "RCTBattery.h"
//
//@implementation RCTBattery
//
//@end

#import "RCTBattery.h"
#import <UIKit/UIKit.h>

@implementation RCTBattery

// This RCT (React) "macro" exposes the current module to JavaScript
RCT_EXPORT_MODULE(ReactBattery);

// We must explicitly expose methods otherwise JavaScript can't access anything
RCT_EXPORT_METHOD(isCharging:(RCTResponseSenderBlock)callback)
{
    [[UIDevice currentDevice] setBatteryMonitoringEnabled:YES];
    
    if ([[UIDevice currentDevice] batteryState] == UIDeviceBatteryStateCharging) {
        callback(@[@"true"]);
    } else {
        callback(@[@"false"]);
    }
}

@end
