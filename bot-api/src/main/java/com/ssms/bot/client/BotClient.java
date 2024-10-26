package com.ssms.bot.client;

import com.ssms.bot.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.ssms.bot.BotConstant;
import com.ssms.common.api.BaseResponse;

@FeignClient(name = BotConstant.SERVICE_NAME, path = "/v1", url = "${ssms.bot-service-endpoint}")
public interface BotClient {

    @PostMapping(path="sms_greeting")
    BaseResponse sendSmsGreeting(@RequestBody @Validated GreetingRequest request);

    @PostMapping(path="onboard_worker")
    BaseResponse onboardWorker(@RequestBody @Validated OnboardWorkerRequest request);

    @PostMapping(path="alert_new_shift")
    BaseResponse alertNewShift(@RequestBody @Validated AlertNewShiftRequest request);

    @PostMapping(path="alert_new_shifts")
    BaseResponse alertNewShifts(@RequestBody @Validated AlertNewShiftsRequest request);

    @PostMapping(path="alert_removed_shift")
    BaseResponse alertRemovedShift(@RequestBody @Validated AlertRemovedShiftRequest request);

    @PostMapping(path="alert_removed_shifts")
    BaseResponse alertRemovedShifts(@RequestBody @Validated AlertRemovedShiftsRequest request);

    @PostMapping(path="alert_changed_shifts")
    BaseResponse alertChangedShift(@RequestBody @Validated AlertChangedShiftRequest request);
}
