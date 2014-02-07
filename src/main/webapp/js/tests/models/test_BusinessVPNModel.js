
"use strict";

define(['QUnit', 'models/BusinessVPNModel'], function (QUnit, BusinessVPNModel) {

    QUnit.module("BusinessVPNModel");

    QUnit.test("PRODUCT_CODE", function () {
        QUnit.strictEqual(BusinessVPNModel.PRODUCT_CODE, "115.00");
    });

    QUnit.test("plain properties", function () {
        var model = new BusinessVPNModel({ id: 5, product: { town: "town1", site: { address: "address1" }, port: { coverage: "coverage1", type: "type1", speed: 2048, tarifficationScheme: "tarifficationScheme1"}} });
        //
        QUnit.strictEqual(model.get("itemId"), 5);
        QUnit.strictEqual(model.get("productSiteAddress"), "address1");
        QUnit.strictEqual(model.get("productTown"), "town1");
        QUnit.strictEqual(model.get("productPortCoverage"), "coverage1");
        QUnit.strictEqual(model.get("productPortType"), "type1");
        QUnit.strictEqual(model.get("productPortSpeed"), 2048);
        QUnit.strictEqual(model.get("productPortTarifficationScheme"), "tarifficationScheme1");
        //
        model.set("productPortCoverage", "coverage2");
        model.set("productPortType", "type2");
        model.set("productPortSpeed", 4096);
        model.set("productPortTarifficationScheme", "tarifficationScheme2");
        //
        QUnit.strictEqual(model.get("productPortCoverage"), "coverage2");
        QUnit.strictEqual(model.get("productPortType"), "type2");
        QUnit.strictEqual(model.get("productPortSpeed"), 4096);
        QUnit.strictEqual(model.get("productPortTarifficationScheme"), "tarifficationScheme2");
    });

    QUnit.test("out_standard_price_payment_onetime_money", function () {
        var model = new BusinessVPNModel({ treasures: [
            {
                "id": 18,
                "code": "out_standard_price_payment_onetime_money",
                "currencyCode": "RUR",
                "value": 9600
            }
        ]
        });
        //
        QUnit.strictEqual(model.get("out_standard_price_payment_onetime_money"), 9600);
        model.set("out_standard_price_payment_onetime_money", 9700)
        QUnit.strictEqual(model.get("out_standard_price_payment_onetime_money"), 9700);
    });

    QUnit.test("monthly_money", function () {
        var dataFix = {
            product: { port: { tarifficationScheme: "fix"} },
            treasures: [
            {
                "id": 19,
                "code": "out_standard_price_fix_payment_monthly_money",
                "currencyCode": "RUR",
                "value": 26500
            },
            {
                "id": 24,
                "code": "in_required_price_ubb_payment_monthly_money",
                "currencyCode": "RUR",
                "value": 1500
            },
        ]
        };
        //
        var dataUbb = {
            product: { port: { tarifficationScheme: "ubb"} },
            treasures: [
            {
                "id": 19,
                "code": "out_standard_price_fix_payment_monthly_money",
                "currencyCode": "RUR",
                "value": 26500
            },
            {
                "id": 24,
                "code": "in_required_price_ubb_payment_monthly_money",
                "currencyCode": "RUR",
                "value": 1500
            },
        ]
        };
        //
        var modelFix = new BusinessVPNModel(dataFix);
        var modelUbb = new BusinessVPNModel(dataUbb);
        //
        QUnit.strictEqual(modelFix.get("monthly_money"), 26500);
        QUnit.strictEqual(modelUbb.get("monthly_money"), 1500);
        //
        modelFix.set("monthly_money", 9700)
        modelUbb.set("monthly_money", 2000)
        //
        QUnit.strictEqual(modelFix.get("monthly_money"), 9700);
        QUnit.strictEqual(modelUbb.get("monthly_money"), 2000);
        //
        QUnit.strictEqual(dataFix.treasures[0].value, 9700);
        QUnit.strictEqual(dataFix.treasures[1].value, 1500);
        //
        QUnit.strictEqual(dataUbb.treasures[0].value, 26500);
        QUnit.strictEqual(dataUbb.treasures[1].value, 2000);
    });


    QUnit.test("ref_tariff_ubb_data3_1MB_money", function () {
        var dataFix = {
            product: { port: { tarifficationScheme: "fix"} },
            treasures: [
                {
                    "id": 9,
                    "code": "ref_tariff_ubb_data3_1MB_money",
                    "currencyCode": "RUR",
                    "value": 0.44
                },
        ]
        };
        //
        var dataUbb = {
            product: { port: { tarifficationScheme: "ubb"} },
            treasures: [
                {
                    "id": 9,
                    "code": "ref_tariff_ubb_data3_1MB_money",
                    "currencyCode": "RUR",
                    "value": 0.44
                },
        ]
        };
        //
        var modelFix = new BusinessVPNModel(dataFix);
        var modelUbb = new BusinessVPNModel(dataUbb);
        //
        QUnit.strictEqual(modelFix.get("ref_tariff_ubb_data3_1MB_money"), "");
        QUnit.strictEqual(modelUbb.get("ref_tariff_ubb_data3_1MB_money"), 0.44);
    });

    QUnit.test("ref_tariff_ubb_data2_1MB_money", function () {
        var dataFix = {
            product: { port: { tarifficationScheme: "fix"} },
            treasures: [
                {
                    "id": 9,
                    "code": "ref_tariff_ubb_data2_1MB_money",
                    "currencyCode": "RUR",
                    "value": 0.44
                },
        ]
        };
        //
        var dataUbb = {
            product: { port: { tarifficationScheme: "ubb"} },
            treasures: [
                {
                    "id": 9,
                    "code": "ref_tariff_ubb_data2_1MB_money",
                    "currencyCode": "RUR",
                    "value": 0.44
                },
        ]
        };
        //
        var modelFix = new BusinessVPNModel(dataFix);
        var modelUbb = new BusinessVPNModel(dataUbb);
        //
        QUnit.strictEqual(modelFix.get("ref_tariff_ubb_data2_1MB_money"), "");
        QUnit.strictEqual(modelUbb.get("ref_tariff_ubb_data2_1MB_money"), 0.44);
    });

    QUnit.test("ref_tariff_ubb_data1_1MB_money", function () {
        var dataFix = {
            product: { port: { tarifficationScheme: "fix"} },
            treasures: [
                {
                    "id": 9,
                    "code": "ref_tariff_ubb_data1_1MB_money",
                    "currencyCode": "RUR",
                    "value": 0.44
                },
        ]
        };
        //
        var dataUbb = {
            product: { port: { tarifficationScheme: "ubb"} },
            treasures: [
                {
                    "id": 9,
                    "code": "ref_tariff_ubb_data1_1MB_money",
                    "currencyCode": "RUR",
                    "value": 0.44
                },
        ]
        };
        //
        var modelFix = new BusinessVPNModel(dataFix);
        var modelUbb = new BusinessVPNModel(dataUbb);
        //
        QUnit.strictEqual(modelFix.get("ref_tariff_ubb_data1_1MB_money"), "");
        QUnit.strictEqual(modelUbb.get("ref_tariff_ubb_data1_1MB_money"), 0.44);
    });

    QUnit.test("ref_tariff_ubb_voice_1MB_money", function () {
        var dataFix = {
            product: { port: { tarifficationScheme: "fix"} },
            treasures: [
                {
                    "id": 9,
                    "code": "ref_tariff_ubb_voice_1MB_money",
                    "currencyCode": "RUR",
                    "value": 0.44
                },
        ]
        };
        //
        var dataUbb = {
            product: { port: { tarifficationScheme: "ubb"} },
            treasures: [
                {
                    "id": 9,
                    "code": "ref_tariff_ubb_voice_1MB_money",
                    "currencyCode": "RUR",
                    "value": 0.44
                },
        ]
        };
        //
        var modelFix = new BusinessVPNModel(dataFix);
        var modelUbb = new BusinessVPNModel(dataUbb);
        //
        QUnit.strictEqual(modelFix.get("ref_tariff_ubb_voice_1MB_money"), "");
        QUnit.strictEqual(modelUbb.get("ref_tariff_ubb_voice_1MB_money"), 0.44);
    });

    QUnit.test("ref_tariff_ubb_video_1MB_money", function () {
        var dataFix = {
            product: { port: { tarifficationScheme: "fix"} },
            treasures: [
                {
                    "id": 9,
                    "code": "ref_tariff_ubb_video_1MB_money",
                    "currencyCode": "RUR",
                    "value": 0.44
                },
        ]
        };
        //
        var dataUbb = {
            product: { port: { tarifficationScheme: "ubb"} },
            treasures: [
                {
                    "id": 9,
                    "code": "ref_tariff_ubb_video_1MB_money",
                    "currencyCode": "RUR",
                    "value": 0.44
                },
        ]
        };
        //
        var modelFix = new BusinessVPNModel(dataFix);
        var modelUbb = new BusinessVPNModel(dataUbb);
        //
        QUnit.strictEqual(modelFix.get("ref_tariff_ubb_video_1MB_money"), "");
        QUnit.strictEqual(modelUbb.get("ref_tariff_ubb_video_1MB_money"), 0.44);
    });


});