// @ts-nocheck
export default {
  async fetch(request) {
    // ======================== 1. 地图层列表（基于 v10.3 Map Layers） ========================
    const LAYERS_LIST = [
      "AlBasrah_AAS_v1","AlBasrah_AAS_v2","AlBasrah_AAS_v3_CL","AlBasrah_Invasion_v1","AlBasrah_Invasion_v2","AlBasrah_Invasion_v3",
      "AlBasrah_RAAS_v1","AlBasrah_RAAS_v2","AlBasrah_Seed_v1","AlBasrah_Seed_v2","AlBasrah_Skirmish_v1","AlBasrah_Skirmish_v2","AlBasrah_Skirmish_v3",
      "Anvil_AAS_v1","Anvil_Invasion_v1","Anvil_RAAS_v1","Anvil_RAAS_v2","Anvil_Skirmish_v1",
      "BlackCoast_AAS_v1","BlackCoast_AAS_v2","BlackCoast_Invasion_v1","BlackCoast_Invasion_v2","BlackCoast_Invasion_v3",
      "BlackCoast_RAAS_v1","BlackCoast_RAAS_v2","BlackCoast_Seed_v1","BlackCoast_Skirmish_v1",
      "Chora_AAS_v1","Chora_AAS_v2","Chora_AAS_v3","Chora_Insurgency_v1","Chora_Invasion_v1","Chora_Invasion_v2",
      "Chora_RAAS_v1","Chora_Skirmish_v1","Chora_TC_v1",
      "Fallujah_AAS_v1","Fallujah_Insurgency_v1","Fallujah_Invasion_v1","Fallujah_Invasion_v2","Fallujah_RAAS_v1","Fallujah_RAAS_v2",
      "Fallujah_Seed_v1","Fallujah_Skirmish_v1","Fallujah_Skirmish_v2","Fallujah_TC_v1",
      "FoolsRoad_AAS_v1","FoolsRoad_AAS_v2","FoolsRoad_Destruction_v1","FoolsRoad_Invasion_v1","FoolsRoad_RAAS_v1",
      "FoolsRoad_Skirmish_v1","FoolsRoad_Skirmish_v2","FoolsRoad_TC_v1",
      "GooseBay_AAS_v1","GooseBay_Invasion_v1","GooseBay_Invasion_v2","GooseBay_RAAS_v1","GooseBay_RAAS_v2","GooseBay_Seed_v1","GooseBay_Skirmish_v1",
      "Gorodok_AAS_v1","Gorodok_Destruction_v1","Gorodok_Insurgency_v1","Gorodok_Invasion_v1","Gorodok_Invasion_v2","Gorodok_Invasion_v3",
      "Gorodok_RAAS_v1","Gorodok_RAAS_v2","Gorodok_Skirmish_v1","Gorodok_TC_v1",
      "Harju_AAS_v1","Harju_AAS_v2","Harju_AAS_v3","Harju_Invasion_v1","Harju_Invasion_v2","Harju_Invasion_v3",
      "Harju_RAAS_v1","Harju_RAAS_v2","Harju_RAAS_v3_CL","Harju_TC_v1","Harju_Seed_v1","Harju_Skirmish_v1","Harju_Skirmish_v2",
      "JensensRange_ADF-PLAAGF","JensensRange_AFU-RGF","JensensRange_BAF-IMF","JensensRange_CAF-MEI","JensensRange_CRF-VDV","JensensRange_USA-PLA","JensensRange_USMC-GFI","JensensRange_WPMC-TLF",
      "Kamdesh_AAS_v1","Kamdesh_Insurgency_v1","Kamdesh_Invasion_v1","Kamdesh_RAAS_v1","Kamdesh_Skirmish_v1","Kamdesh_TC_v1",
      "Kohat_AAS_v1","Kohat_RAAS_v1","Kohat_RAAS_v2","Kohat_Skirmish_v1","Kohat_Insurgency_v1","Kohat_Invasion_v1","Kohat_Invasion_v2","Kohat_TC_v1",
      "Kokan_AAS_v1","Kokan_AAS_v2","Kokan_Insurgency_v1","Kokan_Invasion_v1","Kokan_RAAS_v1","Kokan_Skirmish_v1","Kokan_TC_v1",
      "Lashkar_AAS_v1","Lashkar_AAS_v2","Lashkar_Insurgency_v1","Lashkar_Invasion_v1","Lashkar_RAAS_v1","Lashkar_Skirmish_v1","Lashkar_TC_v1","Lashkar_TC_v2",
      "Logar_AAS_v1","Logar_Insurgency_v1","Logar_RAAS_v1","Logar_Seed_v1","Logar_Skirmish_v1","Logar_TC_v1",
      "Manicouagan_AAS_v1","Manicouagan_AAS_v2","Manicouagan_AAS_v3","Manicouagan_RAAS_v1","Manicouagan_RAAS_v2",
      "Manicouagan_Invasion_v1","Manicouagan_Invasion_v2","Manicouagan_Seed_v1","Manicouagan_Seed_v2_CL",
      "Manicouagan_Skirmish_v1","Manicouagan_Skirmish_v2","Manicouagan_Skirmish_v3",
      "Mestia_AAS_v1","Mestia_AAS_v2","Mestia_Invasion_v1","Mestia_RAAS_v1","Mestia_Skirmish_v1","Mestia_TC_v1",
      "Mutaha_AAS_v1","Mutaha_AAS_v2","Mutaha_Invasion_v1","Mutaha_RAAS_v1","Mutaha_RAAS_v2","Mutaha_Seed_v1","Mutaha_Skirmish_v1","Mutaha_TC_v1",
      "Narva_AAS_v1","Narva_AAS_v2","Narva_AAS_v3","Narva_Destruction_v1","Narva_Invasion_v1","Narva_Invasion_v2","Narva_RAAS_v1","Narva_Skirmish_v1","Narva_TC_v1",
      "PacificProvingGrounds_AAS_v1","PacificProvingGrounds_Seed_v1","PacificProvingGrounds_PLANMC-VDV","PacificProvingGrounds_USMC-PLA","PacificProvingGrounds_USMC-RGF",
      "Sanxian_AAS_v1","Sanxian_AAS_v2","Sanxian_AAS_v3","Sanxian_Invasion_v1","Sanxian_Invasion_v2","Sanxian_Invasion_v3",
      "Sanxian_RAAS_v1","Sanxian_RAAS_v2","Sanxian_RAAS_v3_CL","Sanxian_Seed_v1","Sanxian_Skirmish_v1",
      "Skorpo_Invasion_v1","Skorpo_Invasion_v2","Skorpo_Invasion_v3","Skorpo_RAAS_v1","Skorpo_Skirmish_v1",
      "Sumari_AAS_v1","Sumari_AAS_v2","Sumari_AAS_v3","Sumari_Insurgency_v1","Sumari_Invasion_v1","Sumari_RAAS_v1","Sumari_Seed_v1","Sumari_Skirmish_v1","Sumari_TC_v1",
      "Tallil_AAS_v1","Tallil_Invasion_v1","Tallil_RAAS_v1","Tallil_RAAS_v2","Tallil_Seed_v1","Tallil_Skirmish_v1","Tallil_Skirmish_v2","Tallil_Skirmish_v3","Tallil_TC_v1",
      "Yehorivka_AAS_v1","Yehorivka_AAS_v2","Yehorivka_Destruction_v1","Yehorivka_Invasion_v1","Yehorivka_Invasion_v2",
      "Yehorivka_Skirmish_v1","Yehorivka_Skirmish_v2","Yehorivka_RAAS_v1","Yehorivka_RAAS_v2","Yehorivka_TC_v1","Yehorivka_TC_v2"
    ];

    // ======================== 2. 阵营列表 ========================
    const FACTIONS = [
      { label: "乌克兰陆军", value: "AFU" }, { label: "澳大利亚国防军", value: "ADF" }, { label: "英国陆军", value: "BAF" },
      { label: "加拿大陆军", value: "CAF" }, { label: "美国陆军", value: "USA" }, { label: "美国海军陆战队", value: "USMC" },
      { label: "俄罗斯陆军", value: "RGF" }, { label: "俄罗斯空降军", value: "VDV" }, { label: "人民解放军", value: "PLA" },
      { label: "人民解放军陆军两栖旅", value: "PLAAGF" }, { label: "人民解放军海军陆战队", value: "PLANMC" },
      { label: "加拿大抵抗力量", value: "CRF" }, { label: "伊朗", value: "GFI" }, { label: "非正规民兵", value: "IMF" },
      { label: "叛乱军队", value: "MEI" }, { label: "土耳其陆军", value: "TLF" }, { label: "西方雇佣兵", value: "WPMC" }
    ];

    // ======================== 3. 兵种列表及阵营限制 ========================
    const ALL_KITS = [
      { label: "空中突击", value: "AirAssault" }, { label: "装甲", value: "Armored" }, { label: "合成化", value: "CombinedArms" },
      { label: "轻步兵", value: "LightInfantry" }, { label: "机械化", value: "Mechanized" }, { label: "摩托化", value: "Motorized" },
      { label: "勤务保障", value: "Support" }, { label: "两栖", value: "AmphibiousAssault" }
    ];

    const FACTION_KITS_MAP = {
      "AFU": ["AmphibiousAssault", "AirAssault", "Armored", "CombinedArms", "LightInfantry", "Mechanized", "Motorized", "Support"],
      "ADF": ["AirAssault", "CombinedArms", "Mechanized"],
      "BAF": ["AirAssault", "Armored", "CombinedArms", "Mechanized", "Support"],
      "CAF": ["AirAssault", "Armored", "CombinedArms", "Mechanized", "Motorized", "Support"],
      "CRF": ["CombinedArms"],
      "GFI": ["AirAssault", "Armored", "CombinedArms", "LightInfantry", "Mechanized", "Support"],
      "IMF": ["Armored", "CombinedArms", "LightInfantry", "Mechanized", "Motorized", "Support"],
      "MEI": ["Armored", "CombinedArms", "LightInfantry", "Mechanized", "Motorized", "Support"],
      "PLA": ["AirAssault", "Armored", "CombinedArms", "LightInfantry", "Motorized", "Support"],
      "PLAAGF": ["AmphibiousAssault", "Armored", "CombinedArms", "Mechanized"],
      "PLANMC": ["AmphibiousAssault", "AirAssault", "Armored", "CombinedArms", "LightInfantry", "Motorized", "Support"],
      "RGF": ["AmphibiousAssault", "Armored", "CombinedArms", "LightInfantry", "Mechanized", "Motorized", "Support"],
      "TLF": ["AirAssault", "Armored", "CombinedArms", "Mechanized", "Motorized", "Support"],
      "USA": ["AirAssault", "Armored", "CombinedArms", "LightInfantry", "Mechanized", "Motorized", "Support"],
      "USMC": ["AmphibiousAssault", "Armored", "CombinedArms", "LightInfantry", "Motorized", "Support"],
      "VDV": ["AmphibiousAssault", "AirAssault", "Armored", "CombinedArms", "Mechanized", "Support"],
      "WPMC": ["AirAssault", "CombinedArms", "LightInfantry"]
    };
    for (const f of FACTIONS.map(f=>f.value)) {
      if (!FACTION_KITS_MAP[f]) FACTION_KITS_MAP[f] = ALL_KITS.map(k=>k.value);
    }

    // ======================== 4. 地图阵营限制（基于 Biomes） ========================
    const MAP_FACTION_RESTRICTIONS = {
      afghanistan: { maps: ["Anvil", "Chora", "Kamdesh", "Kohat", "Kokan", "Lashkar", "Logar", "Sumari"], factions: ["AFU","ADF","BAF","CAF","GFI","MEI","PLA","PLAAGF","PLANMC","RGF","TLF","USA","USMC","VDV","WPMC","CRF","IMF"] },
      middle_east: { maps: ["AlBasrah", "Fallujah", "Mutaha", "Tallil"], factions: ["AFU","ADF","BAF","CAF","GFI","MEI","IMF","PLA","PLAAGF","PLANMC","RGF","TLF","USA","USMC","VDV","WPMC"] },
      eastern_europe: { maps: ["BlackCoast", "FoolsRoad", "Gorodok", "Mestia", "Yehorivka"], factions: ["AFU","ADF","BAF","CAF","GFI","IMF","PLA","PLAAGF","PLANMC","RGF","TLF","USA","USMC","VDV","WPMC"] },
      northern_europe: { maps: ["Harju", "Narva", "Skorpo"], factions: ["AFU","ADF","BAF","CAF","IMF","PLA","PLAAGF","PLANMC","RGF","TLF","USA","USMC","VDV","WPMC"] },
      north_america: { maps: ["GooseBay", "Manicouagan"], factions: ["AFU","ADF","BAF","CAF","CRF","PLA","PLAAGF","PLANMC","RGF","TLF","USA","USMC","VDV","WPMC"] },
      asia: { maps: ["Sanxian", "PacificProvingGrounds"], factions: ["AFU","ADF","BAF","CAF","PLA","PLAAGF","PLANMC","RGF","TLF","USA","USMC","VDV","WPMC"] }
    };
    const SPECIAL_MAPS = ["JensensRange"];
    const mapToFactions = new Map();
    for (const region of Object.values(MAP_FACTION_RESTRICTIONS)) {
      for (const map of region.maps) mapToFactions.set(map, region.factions);
    }
    for (const sp of SPECIAL_MAPS) mapToFactions.set(sp, FACTIONS.map(f => f.value));

    // ======================== 5. 封禁时长选项 ========================
    const BAN_TIMES = [
      { label: "1天", value: "1d" }, { label: "3天", value: "3d" },
      { label: "1周", value: "1w" }, { label: "1月", value: "1M" }, { label: "永久", value: "0" }
    ];

    // ======================== 6. 美化的 HTML 页面（动态 Toast） ========================
    const html = `<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <title>Squad OP 指令助手 Pro | 战术指挥工具</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Inter:opsz,wght@14..32,400;14..32,500;14..32,600;14..32,700;14..32,800&display=swap" rel="stylesheet">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body {
            font-family: 'Inter', system-ui, -apple-system, sans-serif;
            background: radial-gradient(circle at 10% 20%, hsla(46, 100%, 58%, 0.46), rgba(2, 6, 23, 1));
            min-height: 100vh;
            backdrop-filter: blur(2px);
        }
        ::-webkit-scrollbar { width: 8px; height: 8px; }
        ::-webkit-scrollbar-track { background: rgba(255, 201, 73, 0.67); border-radius: 10px; }
        ::-webkit-scrollbar-thumb { background: linear-gradient(135deg, #f59e0b, #d97706); border-radius: 10px; }
        ::-webkit-scrollbar-thumb:hover { background: #fbbf24; }
        .glass-card {
            background: rgba(255, 255, 255, 0.92);
            backdrop-filter: blur(12px);
            border: 1px solid rgba(255, 255, 255, 0.4);
            box-shadow: 0 8px 32px 0 rgba(0, 17, 255, 0.2);
            transition: all 0.2s ease;
        }
        .glass-card:hover {
            box-shadow: 0 12px 40px 0 rgba(255, 0, 0, 0.25);
            border-color: rgba(245, 158, 11, 0.3);
        }
        .input-focus:focus {
            transform: translateY(-1px);
            box-shadow: 0 8px 20px -6px rgba(245, 158, 11, 0.4);
            border-color: #f59e0b;
            outline: none;
        }
        .btn-press:active { transform: scale(0.97); transition: transform 0.05s; }
        .cmd-block {
            transition: all 0.2s cubic-bezier(0.2, 0.9, 0.4, 1.1);
        }
        .cmd-block:hover {
            background: #1e293b !important;
            transform: translateX(4px);
            box-shadow: 0 4px 12px rgba(0,0,0,0.3);
        }
        .history-item {
            transition: all 0.15s ease;
            background: rgba(0,0,0,0.05);
            border-radius: 9999px;
            padding: 0.25rem 1rem;
            font-size: 0.7rem;
            cursor: pointer;
            backdrop-filter: blur(4px);
        }
        .history-item:hover {
            background: #f59e0b;
            color: white;
            transform: scale(1.05);
            box-shadow: 0 2px 8px rgba(245,158,11,0.4);
        }
        select, input {
            transition: all 0.2s;
        }
        select:focus, input:focus {
            outline: none;
            border-color: #f59e0b;
            box-shadow: 0 0 0 2px rgba(245,158,11,0.2);
        }
        .special-hint {
            background: linear-gradient(135deg, #fef3c7, #fde68a);
            border-left: 4px solid #f59e0b;
        }
        @media (max-width: 768px) {
            .glass-card { backdrop-filter: blur(8px); }
        }
        .cmd-display {
            font-family: 'JetBrains Mono', 'Fira Code', monospace;
            font-size: 0.7rem;
            letter-spacing: 0.3px;
        }
        .toast-modern {
            background: rgba(15, 23, 42, 0.95);
            backdrop-filter: blur(16px);
            border: 1px solid rgba(245, 158, 11, 0.3);
            box-shadow: 0 10px 25px -5px rgba(0, 0, 0, 0.3), 0 0 0 1px rgba(245, 158, 11, 0.1);
            transition: all 0.3s cubic-bezier(0.34, 1.2, 0.64, 1);
            white-space: nowrap;
        }
    </style>
</head>
<body class="text-slate-800 pb-12">
<div class="max-w-7xl mx-auto p-4 md:p-6">
    <!-- 头部区域 -->
    <header class="flex flex-wrap justify-between items-center gap-3 mb-8 glass-card rounded-2xl px-6 py-4 shadow-xl border border-white/30">
        <div>
            <h1 class="text-2xl md:text-3xl font-extrabold bg-gradient-to-r from-amber-500 to-orange-600 bg-clip-text text-transparent flex items-center gap-2">
                <i class="fa-solid fa-terminal text-amber-500"></i>
                Squad OP 指令助手
                <span class="text-xs font-normal bg-amber-500/20 text-amber-700 px-2 py-0.5 rounded-full">v10.3 战术版</span>
            </h1>
            <p class="text-xs text-slate-600 mt-1 flex items-center gap-2">
                <i class="fa-regular fa-map"></i> 地图阵营自动限制 
                <i class="fa-solid fa-crosshairs ml-1"></i> 兵种动态过滤 
                <i class="fa-regular fa-clock ml-1"></i> 实时时间戳
            </p>
        </div>
        <button onclick="app.resetConfig()" class="text-sm bg-white/30 hover:bg-amber-500/20 backdrop-blur-sm px-4 py-2 rounded-full transition-all flex items-center gap-2 shadow-md">
            <i class="fa-solid fa-arrows-rotate"></i> 重置设置
        </button>
    </header>

    <!-- 快速输入行 -->
    <div class="grid grid-cols-1 lg:grid-cols-12 gap-5 mb-8">
        <div class="lg:col-span-3 glass-card rounded-2xl p-4 shadow-lg">
            <label class="block text-[11px] font-bold text-amber-700 uppercase tracking-wider mb-1"><i class="fa-regular fa-id-card mr-1"></i> 管理名称</label>
            <input type="text" id="adminName" placeholder="你的游戏昵称" class="input-focus w-full text-sm font-medium text-slate-800 bg-transparent border-b border-slate-300 py-1.5 transition">
        </div>
        <div class="lg:col-span-2 glass-card rounded-2xl p-4 shadow-lg">
            <label class="block text-[11px] font-bold text-amber-700 uppercase tracking-wider mb-1"><i class="fa-solid fa-hashtag mr-1"></i> 目标 ID/昵称</label>
            <input type="text" id="targetID" placeholder="64位ID 或 游戏名" class="input-focus w-full text-sm font-mono font-bold text-blue-700 bg-transparent border-b border-slate-300 py-1.5">
        </div>
        <div class="lg:col-span-7 glass-card rounded-2xl p-4 shadow-lg flex flex-wrap gap-3 items-end">
            <div class="flex-1 min-w-[140px]">
                <label class="block text-[11px] font-bold text-amber-700 uppercase flex justify-between">预设理由<button onclick="app.manageReasons()" class="text-[10px] text-blue-600 hover:text-blue-800"><i class="fa-regular fa-pen-to-square"></i> 管理</button></label>
                <select id="reasonSelect" class="w-full text-sm bg-white/60 rounded-lg p-1.5 border border-slate-200 outline-none cursor-pointer"></select>
            </div>
            <div class="flex-[2] min-w-[180px]">
                <label class="block text-[11px] font-bold text-amber-700 uppercase">最终理由</label>
                <input type="text" id="reasonText" placeholder="详细原因..." class="input-focus w-full text-sm bg-transparent border-b border-slate-300 py-1.5">
            </div>
        </div>
    </div>

    <!-- 主功能区：左右布局 -->
    <div class="grid grid-cols-1 lg:grid-cols-2 gap-7">
        <!-- 左侧：地图层生成器 -->
        <div class="glass-card rounded-2xl shadow-xl overflow-hidden border border-white/40">
    <div class="bg-gradient-to-r from-amber-50 to-orange-50 px-4 py-2.5 border-b border-amber-100 flex flex-wrap justify-between items-center gap-2">
        <h2 class="font-bold text-slate-800 text-base"><i class="fa-solid fa-map text-amber-600 mr-2"></i> 地图 & 层级生成器</h2>
        <div class="relative w-44">
            <i class="fa-solid fa-search absolute left-2 top-1/2 -translate-y-1/2 text-slate-400 text-xs"></i>
            <input type="text" id="mapSearch" placeholder="搜索地图" class="text-xs pl-7 pr-3 py-1.5 rounded-full border border-slate-300 focus:border-amber-500 outline-none w-full bg-white/70">
        </div>
    </div>
    <div class="p-4 space-y-3">
        <div class="grid grid-cols-12 gap-2">
            <div class="col-span-5"><label class="text-[10px] text-slate-500 font-bold">地图</label><select id="mapSelect" class="w-full p-2 text-sm border rounded-xl bg-white shadow-sm"></select></div>
            <div class="col-span-4"><label class="text-[10px] text-slate-500 font-bold">模式</label><select id="modeSelect" class="w-full p-2 text-sm border rounded-xl bg-white shadow-sm"></select></div>
            <div class="col-span-3"><label class="text-[10px] text-slate-500 font-bold">版本</label><select id="verSelect" class="w-full p-2 text-sm border rounded-xl bg-white shadow-sm"></select></div>
        </div>
        <div id="factionKitPanel" class="relative bg-gradient-to-br from-slate-50 to-white rounded-xl p-3 border shadow-inner">
            <div class="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 text-xs text-slate-400 bg-white/80 px-2 rounded-full z-0"> VS </div>
            <div class="grid grid-cols-2 gap-4 relative z-10">
                <div>
                    <div class="text-[10px] font-bold text-blue-700 mb-1">阵营 1</div>
                    <select id="facA" class="w-full p-1.5 text-xs border rounded-lg bg-white"></select>
                    <select id="kitA" class="w-full p-1.5 text-xs border rounded-lg bg-white mt-1.5"></select>
                </div>
                <div>
                    <div class="text-[10px] font-bold text-red-700 text-right mb-1">阵营 2</div>
                    <select id="facB" class="w-full p-1.5 text-xs border rounded-lg bg-white text-right"></select>
                    <select id="kitB" class="w-full p-1.5 text-xs border rounded-lg bg-white mt-1.5 text-right"></select>
                </div>
            </div>
        </div>
        <div id="noFactionHint" class="hidden special-hint text-center text-xs p-2 rounded-lg">🔧 特殊层，无需选择阵营/兵种</div>
        <div class="space-y-2">
            <div onclick="app.copyCmd('changeLayer', event)" class="cmd-block cursor-pointer rounded-lg bg-slate-800 hover:bg-slate-700 transition-all">
                <div class="flex justify-between text-xs text-slate-300 px-2 pt-1.5"><span class="font-bold"><i class="fa-regular fa-copy mr-1"></i> 立即切图</span><span class="text-amber-400">点击复制</span></div>
                <div class="bg-slate-800 font-mono text-[11px] p-2 rounded-b-lg cmd-display text-white" id="cmdChangeLayer"></div>
            </div>
            <div onclick="app.copyCmd('nextLayer', event)" class="cmd-block cursor-pointer rounded-lg bg-slate-800 hover:bg-slate-700 transition-all">
                <div class="flex justify-between text-xs text-slate-300 px-2 pt-1.5"><span class="font-bold"><i class="fa-regular fa-copy mr-1"></i> 设置下一张</span><span class="text-amber-400">点击复制</span></div>
                <div class="bg-slate-800 font-mono text-[11px] p-2 rounded-b-lg cmd-display text-white" id="cmdNextLayer"></div>
            </div>
        </div>
        <div class="flex gap-2 pt-1">
            <button onclick="app.copyText('AdminEndMatch', event)" class="flex-1 py-2 bg-red-100 hover:bg-red-200 text-red-700 text-xs font-bold rounded-lg btn-press transition-all">结束本局</button>
            <button onclick="app.copyText('AdminRestartMatch', event)" class="flex-1 py-2 bg-orange-100 hover:bg-orange-200 text-orange-700 text-xs font-bold rounded-lg btn-press transition-all">重开本局</button>
        </div>
    </div>
</div>

        <!-- 右侧：功能卡片组 -->
        <div class="space-y-6">
            <!-- 玩家处分卡片 -->
            <div class="glass-card rounded-2xl shadow-xl overflow-hidden">
                <div class="bg-gradient-to-r from-slate-50 to-slate-100 px-5 py-3 border-b flex justify-between items-center">
                    <h2 class="font-bold text-slate-800"><i class="fa-solid fa-gavel text-amber-600 mr-2"></i> 玩家处分</h2>
                    <label class="flex items-center gap-1 text-xs bg-white/50 px-2 py-1 rounded-full"><input type="checkbox" id="enableTimestamp" class="rounded"> <i class="fa-regular fa-clock"></i> 时间戳</label>
                </div>
                <div class="p-5 space-y-4">
                    <div class="appeal-config p-3 rounded-xl text-xs flex flex-wrap justify-between items-center gap-2">
                        <div><i class="fa-solid fa-envelope text-amber-600"></i> 申诉途径 <input type="text" id="appealPath" placeholder="QQ群 / Discord" class="text-xs border rounded-lg px-2 py-1 w-44"></div>
                        <div><label><input type="checkbox" id="enableAppeal"> 附加</label> <button onclick="app.saveAppealConfig()" class="ml-2 text-blue-600 hover:underline">保存</button></div>
                    </div>
                    <div onclick="app.copyCmd('kick', event)" class="p-3 border rounded-xl cursor-pointer hover:bg-amber-50 transition">
                        <div class="flex justify-between"><span class="font-bold">Kick (踢出)</span><i class="fa-regular fa-copy text-slate-400"></i></div>
                        <code id="kickCmd" class="text-xs font-mono block text-red-600 bg-slate-50 p-1 rounded mt-1"></code>
                    </div>
                    <div onclick="app.copyCmd('ban', event)" class="p-3 border rounded-xl cursor-pointer hover:bg-amber-50 transition">
                        <div class="flex justify-between"><span class="font-bold">Ban (封禁)</span><select id="banTime" class="text-[10px] border rounded-lg bg-white" onclick="event.stopPropagation()"></select></div>
                        <code id="banCmd" class="text-xs font-mono block text-red-700 bg-slate-50 p-1 rounded mt-1"></code>
                    </div>
                    <div onclick="app.copyCmd('warn', event)" class="p-3 border rounded-xl cursor-pointer hover:bg-amber-50 transition">
                        <div class="flex justify-between"><span class="font-bold">Warn (警告)</span><i class="fa-regular fa-copy text-slate-400"></i></div>
                        <code id="warnCmd" class="text-xs font-mono block text-amber-600 bg-slate-50 p-1 rounded mt-1"></code>
                    </div>
                </div>
            </div>

            <!-- 队伍小队广播传送卡片 -->
            <div class="glass-card rounded-2xl shadow-xl overflow-hidden">
                <div class="bg-gradient-to-r from-slate-50 to-slate-100 px-5 py-3 border-b"><h2 class="font-bold"><i class="fa-solid fa-users-gear text-amber-600 mr-2"></i> 队伍 · 小队 · 广播 · 传送</h2></div>
                <div class="p-5 space-y-4">
                    <div class="flex gap-3"><input type="number" id="teamId" placeholder="阵营ID" class="w-1/2 p-2 text-xs border rounded-xl focus:ring-1 focus:ring-amber-400"><input type="number" id="squadId" placeholder="小队ID" class="w-1/2 p-2 text-xs border rounded-xl focus:ring-1 focus:ring-amber-400"></div>
                    <div class="grid grid-cols-2 gap-2">
                        <button onclick="app.quickCmd('AdminForceTeamChange','targetID', event)" class="p-2 bg-indigo-100 hover:bg-indigo-200 rounded-xl text-xs transition btn-press">强切阵营</button>
                        <button onclick="app.quickCmd('AdminRemovePlayerFromSquad','targetID', event)" class="p-2 bg-indigo-100 hover:bg-indigo-200 rounded-xl text-xs transition btn-press">踢出小队</button>
                        <button onclick="app.quickCmd('AdminDemoteCommander','targetID', event)" class="p-2 bg-indigo-100 hover:bg-indigo-200 rounded-xl text-xs transition btn-press">卸任指挥</button>
                        <button onclick="app.getDisbandCmd(event)" class="p-2 bg-red-100 hover:bg-red-200 rounded-xl text-xs transition btn-press">解散小队</button>
                    </div>
                    <div><input type="text" id="broadcastMsg" placeholder="广播消息" class="w-full p-2 text-xs border rounded-xl mb-2"><div onclick="app.copyCmd('broadcast', event)" class="bg-slate-800 text-white text-xs p-2 rounded-xl cursor-pointer cmd-display" id="broadcastCmd"></div></div>
                    <div><input type="text" id="teleportTarget" placeholder="目标玩家名" class="w-full p-2 text-xs border rounded-xl mb-2"><div onclick="app.copyCmd('teleport', event)" class="bg-slate-800 text-white text-xs p-2 rounded-xl cursor-pointer cmd-display" id="teleportCmd"></div></div>
                    <div class="flex gap-2"><button onclick="app.pauseMatch(event)" class="flex-1 py-2 bg-amber-100 hover:bg-amber-200 rounded-xl text-xs transition btn-press">暂停比赛</button><button onclick="app.resumeMatch(event)" class="flex-1 py-2 bg-green-100 hover:bg-green-200 rounded-xl text-xs transition btn-press">恢复比赛</button></div>
                </div>
            </div>

            <!-- 调试开关卡片 -->
            <div class="glass-card rounded-2xl shadow-xl overflow-hidden">
                <div class="bg-gradient-to-r from-slate-50 to-slate-100 px-5 py-3 border-b flex justify-between items-center">
                    <h2 class="font-bold"><i class="fa-solid fa-microchip text-purple-600 mr-2"></i> 服务器调试 / 高级开关</h2>
                    <div class="flex gap-2 items-center"><input type="number" id="slomoValue" value="1" step="0.1" class="w-16 text-xs border rounded-lg p-1"><button onclick="app.copySlomo(event)" class="bg-purple-100 px-2 py-1 rounded-lg text-xs">时间倍数</button></div>
                </div>
                <div class="p-5">
                    <div class="grid grid-cols-1 sm:grid-cols-2 gap-2">
                        <button onclick="app.copyText('AdminDisableVehicleClaiming 1', event)" class="debug-btn flex justify-between p-2.5 bg-slate-50 hover:bg-blue-50 rounded-xl border transition"><span><i class="fa-solid fa-car-side text-blue-500 mr-2"></i>取消载具认领</span><i class="fa-regular fa-copy "></i></button>
                        <button onclick="app.copyText('AdminNoRespawnTimer 1', event)" class="debug-btn flex justify-between p-2.5 bg-slate-50 hover:bg-blue-50 rounded-xl border transition"><span><i class="fa-regular fa-hourglass-half text-blue-500 mr-2"></i>取消复活时间</span><i class="fa-regular fa-copy"></i></button>
                        <button onclick="app.copyText('AdminForceAllVehicleAvailability 1', event)" class="debug-btn flex justify-between p-2.5 bg-slate-50 hover:bg-blue-50 rounded-xl border transition"><span><i class="fa-solid fa-truck-fast text-blue-500 mr-2"></i>取消载具刷新</span><i class="fa-regular fa-copy"></i></button>
                        <button onclick="app.copyText('AdminForceAllDeployableAvailability 1', event)" class="debug-btn flex justify-between p-2.5 bg-slate-50 hover:bg-blue-50 rounded-xl border transition"><span><i class="fa-solid fa-cubes text-blue-500 mr-2"></i>取消部署物限制</span><i class="fa-regular fa-copy"></i></button>
                        <button onclick="app.copyText('AdminForceAllRoleAvailability 1', event)" class="debug-btn flex justify-between p-2.5 bg-slate-50 hover:bg-blue-50 rounded-xl border transition"><span><i class="fa-solid fa-helmet-safety text-blue-500 mr-2"></i>取消兵种限制</span><i class="fa-regular fa-copy"></i></button>
                        <button onclick="app.copyText('AdminDisableVehicleKitRequirement 1', event)" class="debug-btn flex justify-between p-2.5 bg-slate-50 hover:bg-blue-50 rounded-xl border transition"><span><i class="fa-solid fa-gear text-blue-500 mr-2"></i>取消载具角色限制</span><i class="fa-regular fa-copy"></i></button>
                    </div>
                    <div class="mt-4 pt-3 border-t grid grid-cols-2 gap-2">
                        <div><span class="text-[10px] font-semibold">敌方载具使用</span><div class="flex gap-1 mt-1"><button onclick="app.copyText('AdminDisableVehicleTeamRequirement 1', event)" class="flex-1 py-1 bg-purple-100 rounded-lg text-xs btn-press">启用</button><button onclick="app.copyText('AdminDisableVehicleTeamRequirement 0', event)" class="flex-1 py-1 bg-gray-100 rounded-lg text-xs btn-press">禁用</button></div></div>
                        <div class="text-right text-[10px] text-slate-400 italic">更多指令请复制后使用</div>
                    </div>
                </div>
            </div>

            <!-- 最近指令卡片 -->
            <div class="glass-card rounded-2xl shadow-xl overflow-hidden">
                <div class="bg-gradient-to-r from-slate-50 to-slate-100 px-5 py-3 border-b flex justify-between items-center">
                    <span class="text-xs font-bold"><i class="fa-regular fa-clock mr-1"></i> 📋 最近指令</span>
                    <button onclick="app.clearHistory()" class="text-[9px] text-red-500 hover:underline">清空历史</button>
                </div>
                <div class="p-4"><div id="historyContainer" class="flex flex-wrap gap-2 min-h-[40px]"></div></div>
            </div>
        </div>
    </div>
</div>

<!-- Toast 浮窗 - 动态位置 -->
<div id="toast" class="fixed z-50 transition-all duration-300 opacity-0 pointer-events-none toast-modern rounded-full px-5 py-2.5 flex items-center gap-2 shadow-xl" style="top: 0; left: 0;">
    <i class="fa-regular fa-clipboard text-green-400"></i><span id="toastMessage" class="text-white font-semibold">指令已复制</span>
</div>

<script>
(function(){
    // 注入数据
    const LAYERS_LIST = ${JSON.stringify(LAYERS_LIST)};
    const FACTIONS = ${JSON.stringify(FACTIONS)};
    const ALL_KITS = ${JSON.stringify(ALL_KITS)};
    const FACTION_KITS_MAP = ${JSON.stringify(FACTION_KITS_MAP)};
    const MAP_TO_FACTIONS = ${JSON.stringify(Object.fromEntries(mapToFactions))};
    const SPECIAL_MAPS = ${JSON.stringify(SPECIAL_MAPS)};
    const BAN_TIMES = ${JSON.stringify(BAN_TIMES)};
    const DEFAULT_REASONS = ["外挂","无麦带队","毒瘤","数据异常"];
    const MAP_CN = { AlBasrah:"巴士拉", Anvil:"铁砧", BlackCoast:"黑色海岸", Chora:"乔拉", Fallujah:"费卢杰", FoolsRoad:"愚者之路", GooseBay:"鹅湾", Gorodok:"格罗多克", Harju:"哈留", JensensRange:"詹森训练场", Kamdesh:"卡玛德仕高地", Kohat:"科哈特", Kokan:"寇坎", Lashkar:"拉什喀河谷", Logar:"珞珈山谷", Manicouagan:"曼尼古根", Mestia:"梅斯蒂亚", Mutaha:"穆塔哈", Narva:"纳尔瓦", PacificProvingGrounds:"太平洋训练场", Sanxian:"三贤群岛", Skorpo:"斯科普", Sumari:"苏马瑞", Tallil:"塔利尔郊区", Yehorivka:"叶霍里夫卡" };
    const MODE_CN = { AAS:"攻守有序", RAAS:"随机攻防", Invasion:"侵攻", Skirmish:"遭遇战", Seed:"暖服", TC:"区域控制", Destruction:"摧毁", Insurgency:"叛乱" };

    // 构建地图层映射
    const layerMap = new Map();
    for(const full of LAYERS_LIST){
        const parts = full.split('_');
        const map = parts[0];
        if(!layerMap.has(map)) layerMap.set(map,{modes:new Map(),special:[]});
        const entry = layerMap.get(map);
        const mode = parts[1];
        const known = ["AAS","RAAS","Invasion","Skirmish","Seed","TC","Destruction","Insurgency"];
        if(known.includes(mode)){
            if(!entry.modes.has(mode)) entry.modes.set(mode,[]);
            entry.modes.get(mode).push(parts.slice(2).join('_'));
        } else entry.special.push(parts.slice(1).join('_'));
    }
    const MAPS = Array.from(layerMap.keys());
    const $ = id => document.getElementById(id);
    const el = {
        map: $('mapSelect'), mode: $('modeSelect'), ver: $('verSelect'),
        facA: $('facA'), facB: $('facB'), kitA: $('kitA'), kitB: $('kitB'),
        factionPanel: $('factionKitPanel'), noFaction: $('noFactionHint'), search: $('mapSearch'),
        admin: $('adminName'), target: $('targetID'), reasonSelect: $('reasonSelect'), reasonText: $('reasonText'),
        banTime: $('banTime'), broadcast: $('broadcastMsg'), teleport: $('teleportTarget'),
        ts: $('enableTimestamp'), appealPath: $('appealPath'), appealEnable: $('enableAppeal'),
        slomo: $('slomoValue'), cmdChange: $('cmdChangeLayer'), cmdNext: $('cmdNextLayer'),
        kick: $('kickCmd'), ban: $('banCmd'), warn: $('warnCmd'), broad: $('broadcastCmd'), tp: $('teleportCmd'),
        history: $('historyContainer'), toast: $('toast'), toastMsg: $('toastMessage'),
        teamId: $('teamId'), squadId: $('squadId')
    };
    let isSpecial = false, history = JSON.parse(localStorage.getItem('squad_op_history')||'[]');
    let toastTimer = null;

    // 显示 Toast 在指定坐标（鼠标点击位置附近）
    const showToastAt = (msg, clientX, clientY) => {
        if (!el.toastMsg) return;
        if (toastTimer) clearTimeout(toastTimer);
        el.toastMsg.innerText = msg;
        let left = clientX + 10;
        let top = clientY + 20;
        const toastWidth = 180;
        const toastHeight = 40;
        if (left + toastWidth > window.innerWidth) left = window.innerWidth - toastWidth - 10;
        if (top + toastHeight > window.innerHeight) top = window.innerHeight - toastHeight - 10;
        el.toast.style.left = left + 'px';
        el.toast.style.top = top + 'px';
        el.toast.classList.remove('opacity-0', 'pointer-events-none');
        el.toast.classList.add('opacity-100');
        toastTimer = setTimeout(() => {
            el.toast.classList.remove('opacity-100');
            el.toast.classList.add('opacity-0', 'pointer-events-none');
            toastTimer = null;
        }, 1800);
    };

    // 带事件参数的复制（用于点击按钮/区域）
    const copyWithEvent = async (txt, event) => {
        if(!txt) return;
        try {
            await navigator.clipboard.writeText(txt);
            if(!history.includes(txt)){
                history=[txt,...history].slice(0,12);
                localStorage.setItem('squad_op_history',JSON.stringify(history));
                updateHistory();
            }
            showToastAt('✓ 指令已复制', event.clientX, event.clientY);
        } catch(e) {
            showToastAt('❌ 复制失败', event.clientX, event.clientY);
        }
    };

    // 无事件参数的复制（用于管理理由、清空历史等，居中显示）
    const copyWithoutEvent = async (txt) => {
        if(!txt) return;
        try {
            await navigator.clipboard.writeText(txt);
            if(!history.includes(txt)){
                history=[txt,...history].slice(0,12);
                localStorage.setItem('squad_op_history',JSON.stringify(history));
                updateHistory();
            }
            const centerX = window.innerWidth / 2 - 90;
            const centerY = window.innerHeight / 2 - 20;
            showToastAt('✓ 指令已复制', centerX, centerY);
        } catch(e) {
            const centerX = window.innerWidth / 2 - 90;
            const centerY = window.innerHeight / 2 - 20;
            showToastAt('❌ 复制失败', centerX, centerY);
        }
    };

    const updateHistory = () => { el.history.innerHTML = history.slice(0,10).map(c=>'<span class="history-item" data-cmd="'+c.replace(/"/g,'&quot;')+'">'+(c.length>28?c.slice(0,25)+'…':c)+'</span>').join(''); document.querySelectorAll('.history-item').forEach(s=>s.addEventListener('click',(e)=>{ const cmd = s.getAttribute('data-cmd'); copyWithoutEvent(cmd); })); };
    
    // 填充封禁时长选项
    BAN_TIMES.forEach(t => el.banTime.add(new Option(t.label, t.value)));

    function updateKitsByFaction(faction, kitSelect) {
        const allowedKits = FACTION_KITS_MAP[faction] || [];
        const availableKits = ALL_KITS.filter(k => allowedKits.includes(k.value));
        kitSelect.innerHTML = '';
        if (availableKits.length === 0) { kitSelect.add(new Option("无可用兵种", "")); }
        else { availableKits.forEach(k => kitSelect.add(new Option(k.label, k.value))); }
        const currentVal = kitSelect.value;
        if (currentVal && allowedKits.includes(currentVal)) kitSelect.value = currentVal;
        else if (kitSelect.options.length) kitSelect.value = kitSelect.options[0].value;
    }

    function filterFactionsByMap(mapName) {
        const allowed = MAP_TO_FACTIONS[mapName] || FACTIONS.map(f=>f.value);
        const filterOne = (select, kitSelect) => {
            const current = select.value;
            select.innerHTML = '';
            FACTIONS.filter(f => allowed.includes(f.value)).forEach(f => select.add(new Option(f.label, f.value)));
            if (current && allowed.includes(current)) select.value = current;
            else if (select.options.length) select.value = select.options[0].value;
            updateKitsByFaction(select.value, kitSelect);
        };
        filterOne(el.facA, el.kitA);
        filterOne(el.facB, el.kitB);
    }

    function initKits() {
        updateKitsByFaction(el.facA.value, el.kitA);
        updateKitsByFaction(el.facB.value, el.kitB);
    }

    const getMapCN = en => MAP_CN[en]||en;
    const getModeCN = m => MODE_CN[m]||m;
    const updateModes = () => {
        const map = el.map.value, data = layerMap.get(map);
        if(!data) return;
        const hasNormal = data.modes.size>0;
        isSpecial = !hasNormal && data.special.length>0;
        if(isSpecial){
            el.mode.innerHTML = '<option>🔧 特殊层</option>';
            el.mode.disabled = true;
            el.noFaction.classList.remove('hidden');
            el.factionPanel.classList.add('faction-disabled');
            el.ver.innerHTML = '';
            data.special.forEach(v=>el.ver.add(new Option(v,v)));
        } else {
            el.mode.disabled = false;
            el.noFaction.classList.add('hidden');
            el.factionPanel.classList.remove('faction-disabled');
            el.mode.innerHTML = '';
            Array.from(data.modes.keys()).sort().forEach(m=>el.mode.add(new Option(getModeCN(m),m)));
            if(el.mode.options.length) el.mode.value = el.mode.options[0].value;
            updateVersions();
            filterFactionsByMap(map);
        }
        renderLayerCmds();
    };
    const updateVersions = () => {
        if(isSpecial) return;
        const map = el.map.value, mode = el.mode.value, versions = layerMap.get(map)?.modes.get(mode)||[];
        el.ver.innerHTML = '';
        versions.forEach(v=>el.ver.add(new Option(v,v)));
        if(versions.length) el.ver.value = versions[0];
        renderLayerCmds();
    };
    const getFullLayer = () => isSpecial ? el.map.value+'_'+el.ver.value : el.map.value+'_'+el.mode.value+'_'+el.ver.value;
    const renderLayerCmds = () => {
        const full = getFullLayer();
        if(!full) return;
        if(isSpecial){
            el.cmdChange.innerText = 'AdminChangeLayer '+full;
            el.cmdChange.parentElement.setAttribute('data-raw','AdminChangeLayer '+full);
            el.cmdNext.innerText = 'AdminSetNextLayer '+full;
            el.cmdNext.parentElement.setAttribute('data-raw','AdminSetNextLayer '+full);
        } else {
            const fp = el.facA.value+'+'+el.kitA.value+' '+el.facB.value+'+'+el.kitB.value;
            el.cmdChange.innerHTML = '<span class="cmd-command">AdminChangeLayer</span> '+full+' <span class="cmd-string">'+fp+'</span>';
            el.cmdChange.parentElement.setAttribute('data-raw','AdminChangeLayer '+full+' '+fp);
            el.cmdNext.innerHTML = '<span class="cmd-command">AdminSetNextLayer</span> '+full+' <span class="cmd-string">'+fp+'</span>';
            el.cmdNext.parentElement.setAttribute('data-raw','AdminSetNextLayer '+full+' '+fp);
        }
    };
    const refreshPreviews = () => {
        renderLayerCmds();
        const id = el.target.value.trim() || '<ID>', reason = el.reasonText.value.trim() || '违反服务器规则';
        const admin = el.admin.value.trim() || 'Admin';
        const ts = el.ts.checked ? ' ['+new Date().toLocaleString()+']' : '';
        const appeal = (el.appealEnable.checked && el.appealPath.value.trim()) ? ' 申诉途径:'+el.appealPath.value : '';
        const final = reason+' [处理人:'+admin+']'+ts+appeal;
        el.kick.innerText = 'Adminkick "'+id+'" '+final;
        el.ban.innerText = 'Adminban "'+id+'" '+el.banTime.value+' '+final;
        el.warn.innerText = 'AdminWarn "'+id+'" '+reason+' [处理人:Admin]'+ts+appeal;
        const msg = el.broadcast.value.trim();
        el.broad.innerText = 'AdminBroadcast "'+(msg+(ts?ts:'')||'...')+'"';
        el.tp.innerText = 'AdminTeleportToPlayer "'+(el.teleport.value.trim()||'玩家名')+'"';
    };
    const debounce = (fn,delay)=>{ let t; return ()=>{ clearTimeout(t); t=setTimeout(fn,delay); }; };
    const debouncedRefresh = debounce(refreshPreviews,200);
    
    el.map.addEventListener('change', () => { updateModes(); refreshPreviews(); });
    el.mode.addEventListener('change', updateVersions);
    el.ver.addEventListener('change', renderLayerCmds);
    el.facA.addEventListener('change', () => { updateKitsByFaction(el.facA.value, el.kitA); renderLayerCmds(); refreshPreviews(); });
    el.facB.addEventListener('change', () => { updateKitsByFaction(el.facB.value, el.kitB); renderLayerCmds(); refreshPreviews(); });
    el.kitA.addEventListener('change', renderLayerCmds);
    el.kitB.addEventListener('change', renderLayerCmds);
    el.admin.addEventListener('input', () => {
        localStorage.setItem('squad_op_admin_name', el.admin.value);
        debouncedRefresh();
    });
    el.target.addEventListener('input', debouncedRefresh);
    el.reasonText.addEventListener('input', debouncedRefresh);
    el.broadcast.addEventListener('input', debouncedRefresh);
    el.teleport.addEventListener('input', debouncedRefresh);
    el.ts.addEventListener('change', debouncedRefresh);
    el.appealEnable.addEventListener('change', debouncedRefresh);
    el.appealPath.addEventListener('input', debouncedRefresh);
    el.banTime.addEventListener('change', debouncedRefresh);
    el.search.addEventListener('input', function(){ const term=this.value.toLowerCase(); const filtered=MAPS.filter(m=>getMapCN(m).toLowerCase().includes(term)||m.toLowerCase().includes(term)); el.map.innerHTML=''; filtered.forEach(m=>el.map.add(new Option(getMapCN(m),m))); if(filtered.length) el.map.value=filtered[0]; updateModes(); refreshPreviews(); });
    
    const buildReasonSelect = () => {
        const custom = JSON.parse(localStorage.getItem('squad_op_custom_reasons')||'[]');
        const all = [...DEFAULT_REASONS, ...custom.filter(r=>!DEFAULT_REASONS.includes(r))];
        el.reasonSelect.innerHTML = '<option value="">-- 选择预设 --</option>';
        all.forEach(r=>el.reasonSelect.add(new Option(r,r)));
        el.reasonSelect.onchange = () => { if(el.reasonSelect.value) el.reasonText.value = el.reasonSelect.value; refreshPreviews(); };
    };
    buildReasonSelect();
    
    el.map.innerHTML = ''; MAPS.forEach(m=>el.map.add(new Option(getMapCN(m),m)));
    el.map.value = 'Mutaha';
    filterFactionsByMap('Mutaha');
    updateModes();
    initKits();
    // 加载保存的管理名称
    const savedAdminName = localStorage.getItem('squad_op_admin_name');
    if (savedAdminName) el.admin.value = savedAdminName;
    refreshPreviews();
    updateHistory();
    
    const savedPath = localStorage.getItem('squad_op_appeal_path');
    const savedEnable = localStorage.getItem('squad_op_appeal_enabled') === 'true';
    if(savedPath) el.appealPath.value = savedPath;
    if(savedEnable) el.appealEnable.checked = true;
    
    const getPunishCmd = type => {
        const id = el.target.value.trim() || '<ID>', reason = el.reasonText.value.trim() || '违反规则';
        const admin = el.admin.value.trim() || 'Admin';
        const ts = el.ts.checked ? ' ['+new Date().toLocaleString()+']' : '';
        const appeal = (el.appealEnable.checked && el.appealPath.value.trim()) ? ' 申诉途径:'+el.appealPath.value : '';
        const final = reason+' [处理人:'+admin+']'+ts+appeal;
        if(type==='kick') return 'Adminkick "'+id+'" '+final;
        if(type==='ban') return 'Adminban "'+id+'" '+el.banTime.value+' '+final;
        return 'AdminWarn "'+id+'" '+reason+' [处理人:Admin]'+ts+appeal;
    };
    
    const checkNotEmpty = (value, fieldName, event) => {
        if (!value || value.trim() === '' || value === '<ID>') {
            showToastAt('请填写' + fieldName, event.clientX, event.clientY);
            return false;
        }
        return true;
    };
    
    window.app = {
        copyCmd: async (type, event) => {
            if(type === 'changeLayer') {
                if (!isSpecial && (!el.facA.value || !el.facB.value || !el.kitA.value || !el.kitB.value)) {
                    showToastAt('请选择完整的阵营和兵种', event.clientX, event.clientY);
                    return;
                }
                await copyWithEvent(document.querySelector('#cmdChangeLayer').parentElement.getAttribute('data-raw'), event);
            }
            else if(type === 'nextLayer') {
                if (!isSpecial && (!el.facA.value || !el.facB.value || !el.kitA.value || !el.kitB.value)) {
                    showToastAt('请选择完整的阵营和兵种', event.clientX, event.clientY);
                    return;
                }
                await copyWithEvent(document.querySelector('#cmdNextLayer').parentElement.getAttribute('data-raw'), event);
            }
            else if(type === 'kick') {
                if (!checkNotEmpty(el.target.value, '目标ID/昵称', event)) return;
                await copyWithEvent(getPunishCmd('kick'), event);
            }
            else if(type === 'ban') {
                if (!checkNotEmpty(el.target.value, '目标ID/昵称', event)) return;
                await copyWithEvent(getPunishCmd('ban'), event);
            }
            else if(type === 'warn') {
                if (!checkNotEmpty(el.target.value, '目标ID/昵称', event)) return;
                await copyWithEvent(getPunishCmd('warn'), event);
            }
            else if(type === 'broadcast') {
                await copyWithEvent(el.broad.innerText, event);
            }
            else if(type === 'teleport') {
                if (!checkNotEmpty(el.teleport.value, '目标玩家名', event)) return;
                await copyWithEvent(el.tp.innerText, event);
            }
        },
        copyText: async (text, event) => {
            await copyWithEvent(text, event);
        },
        copySlomo: async (event) => {
            await copyWithEvent('AdminSlomo '+(parseFloat(el.slomo.value)||1), event);
        },
        pauseMatch: async (event) => {
            await copyWithEvent('AdminPauseMatch', event);
        },
        resumeMatch: async (event) => {
            await copyWithEvent('AdminResumeMatch', event);
        },
        quickCmd: async (prefix, fieldName, event) => {
            let val = null;
            if (fieldName === 'targetID') val = el.target?.value.trim();
            else val = el[fieldName]?.value.trim();
            if (!checkNotEmpty(val, '目标ID/昵称', event)) return;
            await copyWithEvent(prefix + ' "' + val + '"', event);
        },
        getDisbandCmd: async (event) => {
            let t = el.teamId?.value.trim();
            let s = el.squadId?.value.trim();
            if (!checkNotEmpty(t, '阵营ID', event)) return;
            if (!checkNotEmpty(s, '小队ID', event)) return;
            await copyWithEvent('AdminDisbandSquad ' + t + ' ' + s, event);
        },
        manageReasons: () => {
            let act = prompt('添加: add:理由  删除: remove:理由');
            if(act?.startsWith('add:')){
                let r = act.slice(4).trim();
                if(r){
                    let arr = JSON.parse(localStorage.getItem('squad_op_custom_reasons')||'[]');
                    if(!arr.includes(r)) arr.push(r);
                    localStorage.setItem('squad_op_custom_reasons',JSON.stringify(arr));
                    buildReasonSelect();
                    if(el.reasonSelect.value===r) el.reasonText.value=r;
                    refreshPreviews();
                    const centerX = window.innerWidth / 2 - 90;
                    const centerY = window.innerHeight / 2 - 20;
                    showToastAt('已添加理由', centerX, centerY);
                }
            } else if(act?.startsWith('remove:')){
                let r = act.slice(7).trim();
                if(r){
                    let arr = JSON.parse(localStorage.getItem('squad_op_custom_reasons')||'[]');
                    let newArr = arr.filter(x=>x!==r);
                    localStorage.setItem('squad_op_custom_reasons',JSON.stringify(newArr));
                    buildReasonSelect();
                    if(el.reasonText.value===r) el.reasonText.value='';
                    refreshPreviews();
                    const centerX = window.innerWidth / 2 - 90;
                    const centerY = window.innerHeight / 2 - 20;
                    showToastAt('已删除理由', centerX, centerY);
                }
            } else if(act) alert('格式错误');
        },
        resetConfig: () => { if(confirm('重置所有数据?')){ localStorage.clear(); location.reload(); } },
        clearHistory: () => { history=[]; localStorage.setItem('squad_op_history','[]'); updateHistory(); const centerX = window.innerWidth / 2 - 90; const centerY = window.innerHeight / 2 - 20; showToastAt('历史已清空', centerX, centerY); },
        saveAppealConfig: () => { localStorage.setItem('squad_op_appeal_path',el.appealPath.value); localStorage.setItem('squad_op_appeal_enabled',el.appealEnable.checked); const centerX = window.innerWidth / 2 - 90; const centerY = window.innerHeight / 2 - 20; showToastAt('申诉配置已保存', centerX, centerY); refreshPreviews(); }
    };
})();
<\/script>
</body>
</html>`;
    return new Response(html, { headers: { "content-type": "text/html;charset=UTF-8" } });
  },
};
