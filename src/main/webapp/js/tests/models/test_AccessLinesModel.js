
"use strict";

define(['QUnit', 'models/AccessLinesModel'], function (QUnit, AccessLinesModel) {

    QUnit.module("AccessLinesModel");

    QUnit.test("PRODUCT_CODE", function () {
        QUnit.strictEqual(AccessLinesModel.PRODUCT_CODE, "802.01");
    });

    QUnit.test("plain properties", function () {
        var model = new AccessLinesModel({ id: 5, product: { site: { address: "address1" } } });
        //
        QUnit.strictEqual(model.get("itemId"), 5);
        QUnit.strictEqual(model.get("productSiteAddress"), "address1");
    });

    QUnit.test("in_lines_type_string", function () {
        var model = new AccessLinesModel({ strings: [
            {
                "id": 16,
                "code": "in_lines_type_string",
                "value": "build"
            },
        ]
        });
        //
        QUnit.strictEqual(model.get("in_lines_type_string"), "build");
        model.set("in_lines_type_string", "lease")
        QUnit.strictEqual(model.get("in_lines_type_string"), "lease");
    });


    QUnit.test("in_capex_equipment_money", function () {
        var model = new AccessLinesModel({ treasures: [
            {
                "id": 91,
                "code": "in_capex_equipment_money",
                "currencyCode": "RUR",
                "value": 100000
            },
        ]
        });
        //
        QUnit.strictEqual(model.get("in_capex_equipment_money"), 100000);
        model.set("in_capex_equipment_money", 200000)
        QUnit.strictEqual(model.get("in_capex_equipment_money"), 200000);
    });

    QUnit.test("onetime_money", function () {
        var dataBuild = {
            product: { "type": "build", },
            treasures: [
                {
                    "id": 92,
                    "code": "in_capex_build_money",
                    "currencyCode": "RUR",
                    "value": 200000
                },
                {
                    "id": 93,
                    "code": "in_cost_onetime_lease_money",
                    "currencyCode": "RUR",
                    "value": 300000
                }
        ]
        };
        //
        var dataLease = {
            product: { "type": "lease", },
            treasures: [
                {
                    "id": 92,
                    "code": "in_capex_build_money",
                    "currencyCode": "RUR",
                    "value": 200000
                },
                {
                    "id": 93,
                    "code": "in_cost_onetime_lease_money",
                    "currencyCode": "RUR",
                    "value": 300000
                }
        ]
        };
        //
        var dataOther = {
            product: { "type": "other", },
            treasures: [
                {
                    "id": 92,
                    "code": "in_capex_build_money",
                    "currencyCode": "RUR",
                    "value": 200000
                },
                {
                    "id": 93,
                    "code": "in_cost_onetime_lease_money",
                    "currencyCode": "RUR",
                    "value": 300000
                }
        ]
        };
        //
        var modelBuild = new AccessLinesModel(dataBuild);
        var modelLease = new AccessLinesModel(dataLease);
        var modelOther = new AccessLinesModel(dataOther);
        //
        QUnit.strictEqual(modelBuild.get("onetime_money"), 200000);
        QUnit.strictEqual(modelLease.get("onetime_money"), 300000);
        QUnit.strictEqual(modelOther.get("onetime_money"), "");
        //
        modelBuild.set("onetime_money", 500000)
        modelLease.set("onetime_money", 500000)
        modelOther.set("onetime_money", 500000)
        //
        QUnit.strictEqual(modelBuild.get("onetime_money"), 500000);
        QUnit.strictEqual(modelLease.get("onetime_money"), 500000);
        QUnit.strictEqual(modelOther.get("onetime_money"), "");
        //
        QUnit.strictEqual(dataBuild.treasures[0].value, 500000);
        QUnit.strictEqual(dataBuild.treasures[1].value, 300000);
        //
        QUnit.strictEqual(dataLease.treasures[0].value, 200000);
        QUnit.strictEqual(dataLease.treasures[1].value, 500000);
        //
        QUnit.strictEqual(dataOther.treasures[0].value, 200000);
        QUnit.strictEqual(dataOther.treasures[1].value, 300000);
    });

    QUnit.test("monthly_money", function () {
        var dataBuild = {
            product: { "type": "build", },
            treasures: [
                {
                    "id": 94,
                    "code": "in_cost_monthly_build_money",
                    "currencyCode": "RUR",
                    "value": 200000
                },
                {
                    "id": 95,
                    "code": "in_cost_monthly_lease_money",
                    "currencyCode": "RUR",
                    "value": 300000
                },
        ]
        };
        //
        var dataLease = {
            product: { "type": "lease", },
            treasures: [
                {
                    "id": 94,
                    "code": "in_cost_monthly_build_money",
                    "currencyCode": "RUR",
                    "value": 200000
                },
                {
                    "id": 95,
                    "code": "in_cost_monthly_lease_money",
                    "currencyCode": "RUR",
                    "value": 300000
                },
        ]
        };
        //
        var dataOther = {
            product: { "type": "other", },
            treasures: [
                {
                    "id": 94,
                    "code": "in_cost_monthly_build_money",
                    "currencyCode": "RUR",
                    "value": 200000
                },
                {
                    "id": 95,
                    "code": "in_cost_monthly_lease_money",
                    "currencyCode": "RUR",
                    "value": 300000
                },
        ]
        };
        //
        var modelBuild = new AccessLinesModel(dataBuild);
        var modelLease = new AccessLinesModel(dataLease);
        var modelOther = new AccessLinesModel(dataOther);
        //
        QUnit.strictEqual(modelBuild.get("monthly_money"), 200000);
        QUnit.strictEqual(modelLease.get("monthly_money"), 300000);
        QUnit.strictEqual(modelOther.get("monthly_money"), "");
        //
        modelBuild.set("monthly_money", 500000)
        modelLease.set("monthly_money", 500000)
        modelOther.set("monthly_money", 500000)
        //
        QUnit.strictEqual(modelBuild.get("monthly_money"), 500000);
        QUnit.strictEqual(modelLease.get("monthly_money"), 500000);
        QUnit.strictEqual(modelOther.get("monthly_money"), "");
        //
        QUnit.strictEqual(dataBuild.treasures[0].value, 500000);
        QUnit.strictEqual(dataBuild.treasures[1].value, 300000);
        //
        QUnit.strictEqual(dataLease.treasures[0].value, 200000);
        QUnit.strictEqual(dataLease.treasures[1].value, 500000);
        //
        QUnit.strictEqual(dataOther.treasures[0].value, 200000);
        QUnit.strictEqual(dataOther.treasures[1].value, 300000);
    });

    QUnit.test("inout_required_price_payment_onetime_money", function () {
        var model = new AccessLinesModel({ treasures: [
                {
                    "id": 99,
                    "code": "inout_required_price_payment_onetime_money",
                    "currencyCode": "RUR",
                    "value": 1000
                },
        ]
        });
        //
        QUnit.strictEqual(model.get("inout_required_price_payment_onetime_money"), 1000);
        model.set("inout_required_price_payment_onetime_money", 2000)
        QUnit.strictEqual(model.get("inout_required_price_payment_onetime_money"), 2000);
    });

    QUnit.test("inout_required_price_payment_monthly_money", function () {
        var model = new AccessLinesModel({ treasures: [
                {
                    "id": 100,
                    "code": "inout_required_price_payment_monthly_money",
                    "currencyCode": "RUR",
                    "value": 1000
                },
        ]
        });
        //
        QUnit.strictEqual(model.get("inout_required_price_payment_monthly_money"), 1000);
        model.set("inout_required_price_payment_monthly_money", 2000)
        QUnit.strictEqual(model.get("inout_required_price_payment_monthly_money"), 2000);
    });

    QUnit.test("out_required_price_margin_percent", function () {
        var model = new AccessLinesModel({ numerics: [
                {
                    "id": 103,
                    "code": "out_required_price_margin_percent",
                    "value": 24.0
                },
        ]
        });
        //
        QUnit.strictEqual(model.get("out_required_price_margin_percent"), 24.0);
    });

    QUnit.test("out_required_price_payback_months", function () {
        var model = new AccessLinesModel({ numerics: [
                {
                    "id": 104,
                    "code": "out_required_price_payback_months",
                    "value": 24.0
                },
        ]
        });
        //
        QUnit.strictEqual(model.get("out_required_price_payback_months"), 24.0);
    });

    QUnit.test("out_standard_price_payment_onetime_money", function () {
        var model = new AccessLinesModel({ treasures: [
                {
                    "id": 98,
                    "code": "out_standard_price_payment_onetime_money",
                    "currencyCode": "RUR",
                    "value": 1000
                },
        ]
        });
        //
        QUnit.strictEqual(model.get("out_standard_price_payment_onetime_money"), 1000);
    });

    QUnit.test("out_standard_price_payment_monthly_money", function () {
        var model = new AccessLinesModel({ treasures: [
                {
                    "id": 96,
                    "code": "out_standard_price_payment_monthly_money",
                    "currencyCode": "RUR",
                    "value": 1000
                },
        ]
        });
        //
        QUnit.strictEqual(model.get("out_standard_price_payment_monthly_money"), 1000);
    });

    QUnit.test("out_standard_price_margin_percent", function () {
        var model = new AccessLinesModel({ numerics: [
                {
                    "id": 101,
                    "code": "out_standard_price_margin_percent",
                    "value": 22.0
                },
        ]
        });
        //
        QUnit.strictEqual(model.get("out_standard_price_margin_percent"), 22.0);
    });

    QUnit.test("out_standard_price_payback_months", function () {
        var model = new AccessLinesModel({ numerics: [
                {
                    "id": 102,
                    "code": "out_standard_price_payback_months",
                    "value": 24.0
                },
        ]
        });
        //
        QUnit.strictEqual(model.get("out_standard_price_payback_months"), 24.0);
    });

    QUnit.test("note", function () {
        var model = new AccessLinesModel({ "note": "note1", });
        //
        QUnit.strictEqual(model.get("note"), "note1");
        model.set("note", "note2")
        QUnit.strictEqual(model.get("note"), "note2");
    });


});