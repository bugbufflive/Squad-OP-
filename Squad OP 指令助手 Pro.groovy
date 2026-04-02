export default {
  async fetch(request) {
    const html = `<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Squad OP 指令助手 Pro - 申诉途径版</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link rel="icon" href="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 16 16'%3E%3Ccircle cx='8' cy='8' r='7' fill='%233b82f6'/%3E%3Ctext x='8' y='12' font-size='9' text-anchor='middle' fill='white' font-family='Arial' font-weight='bold'%3ES%3C/text%3E%3C/svg%3E">
    <style>
        body { font-family: 'Inter', system-ui, sans-serif; background-color: #f8fafc02; min-height: 100vh; }
        body { background-image: url('https://img.quad.de5.net/file/hf/xkZ3xD1M.png') !important; background-size: cover !important; background-position: center !important; background-attachment: fixed !important; }
        .input-group { transition: all 0.2s; }
        .input-group:focus-within { transform: translateY(-1px); box-shadow: 0 4px 6px -1px rgba(255, 136, 0, 1); border-color: #3b82f6; }
        .btn-action:active { transform: scale(0.98); }
        .cmd-display .cmd-command { color: #60a5fa; font-weight: 500; }
        .cmd-display .cmd-string { color: #4ade80; }
        ::-webkit-scrollbar { width: 6px; height: 6px; }
        ::-webkit-scrollbar-track { background: #00d9ffff; }
        ::-webkit-scrollbar-thumb { background: #ff00bbff; border-radius: 3px; }
        .history-item { background: #0000000c; border-radius: 12px; padding: 0.25rem 0.75rem; font-size: 0.7rem; max-width: 150px; overflow: hidden; text-overflow: ellipsis; cursor: pointer; }
        .history-item:hover { background: #8800ff50; }
        .timestamp-check { transition: all 0.1s ease; }
        .appeal-config { background: #fef9e3; border-left: 4px solid #f59e0b; }
    </style>
</head>
<body class="text-slate-700 pb-10">

<div class="max-w-5xl mx-auto p-4">
    <header class="flex flex-wrap justify-between items-center mb-6 py-4 border-b border-slate-200 gap-2">
        <div>
            <h1 class="text-2xl font-bold text-slate-800 flex items-center gap-2">
                <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 32 32'%3E%3Ccircle cx='16' cy='16' r='14' fill='%232563eb'/%3E%3Ctext x='16' y='23' font-size='18' text-anchor='middle' fill='white' font-family='Arial' font-weight='bold'%3ES%3C/text%3E%3C/svg%3E" class="w-6 h-6 mr-1" alt="logo">
                <i class="fa-solid fa-terminal text-blue-600"></i> Squad OP 助手 <span class="text-xs bg-blue-100 text-blue-700 px-2 py-0.5 rounded-full">Pro</span>
            </h1>
            <p class="text-xs text-slate-500 mt-1">自动格式化 | 本地存储 | 理由自定义 | 指令历史 | 时间戳追踪 | 申诉途径 | 高级作弊</p>
        </div>
        <div>
            <button onclick="app.resetConfig()" class="text-xs text-slate-400 hover:text-red-500 underline ml-1">重置所有设置</button>
        </div>
    </header>

    <div class="grid grid-cols-1 md:grid-cols-12 gap-4 mb-6">
        <div class="md:col-span-3 bg-white p-3 rounded-lg shadow-sm border border-slate-200 input-group">
            <label class="block text-[10px] font-bold text-slate-400 uppercase tracking-wider mb-1">admin name</label>
            <div class="flex items-center gap-2">
                <i class="fa-solid fa-user-shield text-slate-300"></i>
                <input type="text" id="adminName" placeholder="输入你的游戏昵称" class="w-full text-sm outline-none font-medium text-slate-700">
            </div>
        </div>
        <div class="md:col-span-2 bg-white p-3 rounded-lg shadow-sm border border-slate-200 input-group">
            <label class="block text-[10px] font-bold text-slate-400 uppercase tracking-wider mb-1">Gamers id/name</label>
            <div class="flex items-center gap-2">
                <i class="fa-solid fa-hashtag text-slate-300"></i>
                <input type="text" id="targetID" placeholder="64位/游戏昵称" class="w-full text-sm outline-none font-mono font-bold text-blue-600">
            </div>
        </div>
        <div class="md:col-span-7 bg-white p-3 rounded-lg shadow-sm border border-slate-200 flex flex-wrap gap-3 items-center">
            <div class="flex-1 min-w-[150px]">
                <label class="block text-[10px] font-bold text-slate-400 uppercase tracking-wider mb-1 flex items-center justify-between">
                    预设理由
                    <button onclick="app.manageReasons()" class="text-[9px] text-blue-500 hover:text-blue-700" title="管理自定义理由"><i class="fa-regular fa-pen-to-square"></i> 管理</button>
                </label>
                <select id="reasonSelect" class="w-full text-sm bg-transparent outline-none cursor-pointer"></select>
            </div>
            <div class="w-px h-8 bg-slate-100 hidden sm:block"></div>
            <div class="flex-[2] min-w-[200px]">
                <label class="block text-[10px] font-bold text-slate-400 uppercase tracking-wider mb-1">最终理由</label>
                <input type="text" id="reasonText" placeholder="填写具体原因..." class="w-full text-sm outline-none text-slate-700">
            </div>
        </div>
    </div>

    <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <!-- 左侧：地图层级生成 -->
        <div class="bg-white rounded-xl shadow-sm border border-slate-200 overflow-hidden">
            <div class="bg-slate-50 px-4 py-3 border-b border-slate-200 flex flex-wrap justify-between items-center gap-2">
                <h2 class="font-bold text-slate-700 flex items-center gap-2"><i class="fa-solid fa-map"></i> 地图层级生成</h2>
                <input type="text" id="mapSearch" placeholder="搜索地图..." class="text-xs p-1 px-2 rounded border focus:border-blue-500 outline-none w-40">
            </div>
            <div class="p-5 space-y-4">
                <div class="grid grid-cols-12 gap-2">
                    <div class="col-span-5">
                        <label class="text-[10px] text-slate-400 font-bold uppercase">地图</label>
                        <select id="mapSelect" class="w-full p-2 text-sm border rounded bg-white hover:border-blue-400 transition"></select>
                    </div>
                    <div class="col-span-4">
                        <label class="text-[10px] text-slate-400 font-bold uppercase">模式</label>
                        <select id="modeSelect" class="w-full p-2 text-sm border rounded bg-white hover:border-blue-400 transition"></select>
                    </div>
                    <div class="col-span-3">
                        <label class="text-[10px] text-slate-400 font-bold uppercase">层版本</label>
                        <select id="verSelect" class="w-full p-2 text-sm border rounded bg-white hover:border-blue-400 transition"></select>
                    </div>
                </div>
                <div class="bg-slate-50 rounded-lg p-3 border border-slate-100 relative">
                    <div class="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 text-slate-300 text-xs font-bold z-0">VS</div>
                    <div class="grid grid-cols-2 gap-6 relative z-10">
                        <div class="space-y-1">
                            <div class="text-[10px] font-bold text-blue-600 uppercase flex items-center gap-1">
                                <div class="w-2 h-2 rounded-full bg-blue-500"></div> 阵营 1
                            </div>
                            <select id="facA" class="w-full p-1.5 text-xs border border-blue-100 rounded focus:border-blue-500 outline-none"></select>
                            <select id="kitA" class="w-full p-1.5 text-xs border border-blue-100 rounded focus:border-blue-500 outline-none text-slate-500"></select>
                        </div>
                        <div class="space-y-1">
                            <div class="text-[10px] font-bold text-red-600 uppercase flex items-center gap-1 justify-end">
                                阵营 2 <div class="w-2 h-2 rounded-full bg-red-500"></div>
                            </div>
                            <select id="facB" class="w-full p-1.5 text-xs border border-red-100 rounded focus:border-red-500 outline-none text-right"></select>
                            <select id="kitB" class="w-full p-1.5 text-xs border border-red-100 rounded focus:border-red-500 outline-none text-slate-500 text-right"></select>
                        </div>
                    </div>
                </div>
                <div class="space-y-3 pt-2">
                    <div class="cursor-pointer group" onclick="app.copyCmd('changeLayer')">
                        <div class="flex justify-between text-xs mb-1">
                            <span class="font-bold text-slate-500">立即切图 (ChangeLayer)</span>
                            <span class="text-blue-500 opacity-0 group-hover:opacity-100 transition-opacity"><i class="fa-regular fa-copy"></i> 点击复制</span>
                        </div>
                        <div class="bg-slate-800 font-mono text-xs p-3 rounded shadow-inner break-all hover:bg-slate-700 transition cmd-display" id="cmdChangeLayer"></div>
                    </div>
                    <div class="cursor-pointer group" onclick="app.copyCmd('nextLayer')">
                        <div class="flex justify-between text-xs mb-1">
                            <span class="font-bold text-slate-500">设置下一张 (SetNextLayer)</span>
                            <span class="text-blue-500 opacity-0 group-hover:opacity-100 transition-opacity"><i class="fa-regular fa-copy"></i> 点击复制</span>
                        </div>
                        <div class="bg-slate-800 font-mono text-xs p-3 rounded shadow-inner break-all hover:bg-slate-700 transition cmd-display" id="cmdNextLayer"></div>
                    </div>
                </div>
                <div class="flex gap-2 border-t pt-3 mt-2">
                    <button onclick="app.copyText('AdminEndMatch')" class="flex-1 py-2 bg-red-50 text-red-600 text-xs font-bold rounded border border-red-100 hover:bg-red-100 btn-action">结束本局</button>
                    <button onclick="app.copyText('AdminRestartMatch')" class="flex-1 py-2 bg-orange-50 text-orange-600 text-xs font-bold rounded border border-orange-100 hover:bg-orange-100 btn-action">重开本局</button>
                </div>
            </div>
        </div>

        <!-- 右侧：玩家处分及其他 -->
        <div class="space-y-6">
            <div class="bg-white rounded-xl shadow-sm border border-slate-200 overflow-hidden">
                <div class="bg-slate-50 px-4 py-3 border-b border-slate-200 flex justify-between items-center">
                    <h2 class="font-bold text-slate-700 flex items-center gap-2"><i class="fa-solid fa-gavel"></i> 玩家处分</h2>
                    <label class="flex items-center gap-1 text-xs text-slate-600 cursor-pointer select-none timestamp-check">
                        <input type="checkbox" id="enableTimestamp" class="rounded mr-0.5 cursor-pointer"> 
                        <span><i class="fa-regular fa-clock"></i> 追加时间戳</span>
                    </label>
                </div>
                <div class="p-4 space-y-3">
                    <!-- 申诉途径配置区域 -->
                    <div class="appeal-config p-2 rounded-md text-xs flex flex-wrap items-center justify-between gap-2">
                        <div class="flex items-center gap-2">
                            <i class="fa-solid fa-envelope text-amber-600"></i>
                            <span class="font-medium text-amber-700">申诉途径</span>
                            <input type="text" id="appealPath" placeholder="例如: QQ群 123456 / Discord 邀请链接" class="text-xs border rounded px-2 py-1 w-48 bg-white">
                        </div>
                        <div class="flex items-center gap-2">
                            <label class="flex items-center gap-1">
                                <input type="checkbox" id="enableAppeal" class="rounded"> 附加到理由
                            </label>
                            <button onclick="app.saveAppealConfig()" class="text-blue-500 hover:text-blue-700"><i class="fa-regular fa-save"></i> 保存</button>
                        </div>
                    </div>
                    <div class="p-2 border rounded hover:border-blue-400 cursor-pointer transition group bg-white" onclick="app.copyCmd('kick')">
                        <div class="flex justify-between items-center mb-1">
                            <span class="text-xs font-bold text-slate-500">Kick (踢出)</span>
                            <i class="fa-regular fa-copy text-slate-300 group-hover:text-blue-500"></i>
                        </div>
                        <code id="kickCmd" class="text-xs text-slate-800 font-mono block break-all cmd-display"></code>
                    </div>
                    <div class="p-2 border rounded hover:border-red-400 cursor-pointer transition group bg-white relative" onclick="app.copyCmd('ban')">
                        <div class="flex justify-between items-center mb-1">
                            <span class="text-xs font-bold text-slate-500">Ban (封禁)</span>
                            <select id="banTime" class="text-[10px] border rounded bg-slate-50 px-1 py-0.5 outline-none cursor-pointer hover:bg-white" onclick="event.stopPropagation()"></select>
                        </div>
                        <code id="banCmd" class="text-xs text-red-600 font-mono block break-all cmd-display"></code>
                    </div>
                    <div class="p-2 border rounded hover:border-yellow-400 cursor-pointer transition group bg-white" onclick="app.copyCmd('warn')">
                        <div class="flex justify-between items-center mb-1">
                            <span class="text-xs font-bold text-slate-500">Warn (警告)</span>
                            <i class="fa-regular fa-copy text-slate-300 group-hover:text-yellow-500"></i>
                        </div>
                        <code id="warnCmd" class="text-xs text-yellow-600 font-mono block break-all cmd-display"></code>
                    </div>
                </div>
            </div>

            <div class="bg-white rounded-xl shadow-sm border border-slate-200 overflow-hidden">
                <div class="bg-slate-50 px-4 py-3 border-b border-slate-200">
                    <h2 class="font-bold text-slate-700 flex items-center gap-2"><i class="fa-solid fa-users-gear"></i> 队伍与小队 · 广播 · 传送</h2>
                </div>
                <div class="p-4">
                    <div class="flex gap-2 mb-3">
                        <input type="number" id="teamId" placeholder="阵营 ID (1/2)" class="w-1/2 p-2 text-xs border rounded focus:border-indigo-500 outline-none">
                        <input type="number" id="squadId" placeholder="小队 ID" class="w-1/2 p-2 text-xs border rounded focus:border-indigo-500 outline-none">
                    </div>
                    <div class="grid grid-cols-2 gap-2">
                        <button onclick="app.quickCmd('AdminForceTeamChange', 'targetID')" class="p-2 bg-indigo-50 text-indigo-700 text-xs font-bold rounded hover:bg-indigo-100 text-left btn-action">
                            <i class="fa-solid fa-right-left mr-1"></i> 强切阵营
                        </button>
                        <button onclick="app.quickCmd('AdminRemovePlayerFromSquad', 'targetID')" class="p-2 bg-indigo-50 text-indigo-700 text-xs font-bold rounded hover:bg-indigo-100 text-left btn-action">
                            <i class="fa-solid fa-user-xmark mr-1"></i> 踢出小队
                        </button>
                        <button onclick="app.quickCmd('AdminDemoteCommander', 'targetID')" class="p-2 bg-indigo-50 text-indigo-700 text-xs font-bold rounded hover:bg-indigo-100 text-left btn-action">
                            <i class="fa-solid fa-user-slash mr-1"></i> 卸任指挥
                        </button>
                        <button onclick="app.getDisbandCmd()" class="p-2 bg-red-50 text-red-700 text-xs font-bold rounded hover:bg-red-100 text-left btn-action">
                            <i class="fa-solid fa-ban mr-1"></i> 解散小队
                        </button>
                    </div>
                    <div class="mt-3 pt-3 border-t border-slate-100 grid grid-cols-3 gap-2">
                        <button onclick="app.copyText('AdminDisableVehicleClaiming 1')" class="p-1.5 text-slate-500 text-[10px] bg-slate-100 rounded hover:bg-slate-200">取消载具认领</button>
                        <button onclick="app.copyText('AdminNoRespawnTimer 1')" class="p-1.5 text-slate-500 text-[10px] bg-slate-100 rounded hover:bg-slate-200">取消复活时间</button>
                        <button onclick="app.copyText('AdminForceAllVehicleAvailability 1')" class="p-1.5 text-slate-500 text-[10px] bg-slate-100 rounded hover:bg-slate-200">取消载具刷新时间</button>
                        <button onclick="app.copyText('AdminForceAllDeployableAvailability 1')" class="p-1.5 text-slate-500 text-[10px] bg-slate-100 rounded hover:bg-slate-200">取消部署物限制</button>
                        <button onclick="app.copyText('AdminForceAllRoleAvailability 1')" class="p-1.5 text-slate-500 text-[10px] bg-slate-100 rounded hover:bg-slate-200">取消兵种限制</button>
                        <button onclick="app.copyText('AdminDisableVehicleKitRequirement 1')" class="p-1.5 text-slate-500 text-[10px] bg-slate-100 rounded hover:bg-slate-200">取消载具角色限制</button>
                    </div>

                    <!-- 新增功能：敌方载具 + 时间倍数 -->
                    <div class="mt-3 pt-3 border-t border-slate-100">
                        <h3 class="text-xs font-bold text-slate-500 mb-2"><i class="fa-solid fa-car-side"></i> 高级作弊/调试</h3>
                        <div class="grid grid-cols-2 gap-3 mb-2">
                            <div class="flex flex-col gap-1">
                                <span class="text-[10px] text-slate-400">敌方载具使用</span>
                                <div class="flex gap-1">
                                    <button onclick="app.copyText('AdminDisableVehicleTeamRequirement 1')" class="flex-1 py-1.5 bg-purple-50 text-purple-700 text-[10px] font-bold rounded hover:bg-purple-100 btn-action">启用</button>
                                    <button onclick="app.copyText('AdminDisableVehicleTeamRequirement 0')" class="flex-1 py-1.5 bg-gray-50 text-gray-600 text-[10px] font-bold rounded hover:bg-gray-100 btn-action">禁用</button>
                                </div>
                            </div>
                            <div class="flex flex-col gap-1">
                                <span class="text-[10px] text-slate-400">服务器时间倍数 (Slomo)</span>
                                <div class="flex gap-1 items-center">
                                    <input type="number" id="slomoValue" value="1" min="0" max="20" step="0.1" class="w-20 p-1 text-xs border rounded focus:border-purple-500 outline-none">
                                    <button onclick="app.copySlomo()" class="flex-1 py-1.5 bg-purple-50 text-purple-700 text-[10px] font-bold rounded hover:bg-purple-100 btn-action">复制指令</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="mt-3 pt-3 border-t border-slate-100">
                        <h3 class="text-xs font-bold text-slate-500 mb-2"><i class="fa-solid fa-bullhorn mr-1"></i>广播消息</h3>
                        <div class="flex gap-2 mb-2">
                            <input type="text" id="broadcastMsg" placeholder="输入广播内容..." class="flex-1 p-2 text-xs border rounded focus:border-indigo-500 outline-none">
                        </div>
                        <div class="cursor-pointer group" onclick="app.copyCmd('broadcast')">
                            <div class="flex justify-between text-xs mb-1">
                                <span class="font-bold text-slate-500">AdminBroadcast</span>
                                <span class="text-blue-500 opacity-0 group-hover:opacity-100 transition-opacity"><i class="fa-regular fa-copy"></i> 点击复制</span>
                            </div>
                            <div class="bg-slate-800 font-mono text-xs p-2 rounded shadow-inner break-all hover:bg-slate-700 transition cmd-display" id="broadcastCmd"></div>
                        </div>
                    </div>

                    <div class="mt-3">
                        <h3 class="text-xs font-bold text-slate-500 mb-2"><i class="fa-solid fa-rocket mr-1"></i>传送玩家 (Teleport)</h3>
                        <div class="flex gap-2 mb-2">
                            <input type="text" id="teleportTarget" placeholder="输入目标玩家名称..." class="flex-1 p-2 text-xs border rounded focus:border-indigo-500 outline-none">
                        </div>
                        <div class="cursor-pointer group" onclick="app.copyCmd('teleport')">
                            <div class="flex justify-between text-xs mb-1">
                                <span class="font-bold text-slate-500">AdminTeleportToPlayer</span>
                                <span class="text-blue-500 opacity-0 group-hover:opacity-100 transition-opacity"><i class="fa-regular fa-copy"></i> 点击复制</span>
                            </div>
                            <div class="bg-slate-800 font-mono text-xs p-2 rounded shadow-inner break-all hover:bg-slate-700 transition cmd-display" id="teleportCmd"></div>
                        </div>
                    </div>

                    <div class="mt-4 pt-2 border-t border-slate-200">
                        <div class="flex items-center gap-2 mb-1">
                            <i class="fa-regular fa-clock text-slate-400 text-xs"></i>
                            <span class="text-[10px] font-bold text-slate-400 uppercase">最近指令</span>
                            <button onclick="app.clearHistory()" class="text-[9px] text-slate-400 hover:text-red-400 ml-auto">清空</button>
                        </div>
                        <div id="historyContainer" class="flex flex-wrap gap-1 min-h-[28px]"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="toast" class="fixed top-5 left-1/2 -translate-x-1/2 bg-slate-800 text-white px-4 py-2 rounded-full shadow-lg transform transition-all duration-300 translate-y-[-150%] opacity-0 flex items-center gap-2 z-50">
    <i class="fa-solid fa-check-circle text-green-400"></i>
    <span class="text-sm font-medium" id="toastMessage">指令已复制</span>
</div>

<script>
(function() {
    'use strict';

    // ==================== 常量配置 ====================
    const CONFIG = {
        maps: [
            { label: "巴士拉", value: "AlBasrah" }, { label: "铁砧", value: "Anvil" },
            { label: "贝拉亚山口", value: "BelayaPass" }, { label: "黑色海岸", value: "BlackCoast" },
            { label: "乔拉", value: "Chora" }, { label: "费卢杰", value: "Fallujah" },
            { label: "愚者之路", value: "FoolsRoad" }, { label: "鹅湾", value: "GooseBay" },
            { label: "格罗多克", value: "Gorodok" }, { label: "哈留", value: "Harju" },
            { label: "詹森训练场", value: "JensensRange" }, { label: "卡玛德仕高地", value: "KamdeshHighlands" },
            { label: "科哈特", value: "KohatToi" }, { label: "寇坎", value: "Kokan" },
            { label: "拉什喀河谷", value: "LashkarValley" }, { label: "珞珈山谷", value: "LogarValley" },
            { label: "曼尼古根", value: "Manicouagan" }, { label: "梅斯蒂亚", value: "Mestia" },
            { label: "穆塔哈", value: "Mutaha" }, { label: "纳尔瓦", value: "Narva" },
            { label: "太平洋训练场", value: "PacificProvingGrounds" }, { label: "三贤群岛", value: "Sanxian" },
            { label: "斯科普", value: "Skorpo" }, { label: "苏马瑞", value: "Sumari" },
            { label: "塔利尔郊区", value: "TalilOutskirts" }, { label: "叶霍里夫卡", value: "Yehorivka" }
        ],
        modes: [
            { label: "遭遇战", value: "Skirmish" },
            { label: "随机攻防", value: "RAAS" },
            { label: "侵攻", value: "Invasion" }, 
            { label: "叛乱", value: "Insurgency" },
            { label: "攻守有序", value: "AAS" }, 
            { label: "摧毁", value: "Destruction" },
            { label: "区域控制", value: "TC" }, 
            { label: "暖服", value: "Seed" }
        ],
        factions: [
            { label: "人民解放军", value: "PLA" }, 
            { label: "人民解放军陆军两栖旅", value: "PLAAGF" },
            { label: "人民解放军海军陆战队", value: "PLANMC" }, 
            { label: "美国陆军", value: "USA" },
            { label: "美国海军陆战队", value: "USMC" }, 
            { label: "俄罗斯陆军", value: "RGF" },
            { label: "俄罗斯空降军", value: "VDV" }, 
            { label: "澳大利亚国防军", value: "ADF" },
            { label: "英国陆军", value: "BAF" }, 
            { label: "中东联军", value: "MEA" }, 
            { label: "土耳其陆军", value: "TLF" },
            { label: "乌克兰陆军", value: "AFU" }, 
            { label: "非正规民兵部队", value: "IMF" },
            { label: "叛乱分子", value: "INS" }, 
            { label: "加拿大抵抗力量", value: "CRF" },
            { label: "加拿大陆军", value: "CAF" }
        ],
        kits: [
            { label: "合成化", value: "CombinedArms" },
            { label: "空中突击", value: "AirAssault" },
            { label: "装甲", value: "Armored" }, 
            { label: "轻步兵", value: "LightInfantry" },
            { label: "机械化", value: "Mechanized" }, 
            { label: "摩托化", value: "Motorized" },
            { label: "勤务保障", value: "Support" }
        ],
        banTimes: [
            { label: "1天", value: "1d" }, { label: "3天", value: "3d" },
            { label: "1周", value: "1w" }, { label: "1月", value: "1M" }, { label: "永久", value: "0" }
        ],
        defaultReasons: ["外挂", "无麦带队", "毒瘤", "数据异常"]
    };

    // ==================== DOM 元素缓存 ====================
    const getEl = (id) => document.getElementById(id);
    const el = {
        adminName: getEl('adminName'),
        targetID: getEl('targetID'),
        reasonSelect: getEl('reasonSelect'),
        reasonText: getEl('reasonText'),
        banTime: getEl('banTime'),
        mapSelect: getEl('mapSelect'),
        modeSelect: getEl('modeSelect'),
        verSelect: getEl('verSelect'),
        facA: getEl('facA'),
        facB: getEl('facB'),
        kitA: getEl('kitA'),
        kitB: getEl('kitB'),
        mapSearch: getEl('mapSearch'),
        broadcastMsg: getEl('broadcastMsg'),
        teleportTarget: getEl('teleportTarget'),
        cmdChangeLayer: getEl('cmdChangeLayer'),
        cmdNextLayer: getEl('cmdNextLayer'),
        kickCmd: getEl('kickCmd'),
        banCmd: getEl('banCmd'),
        warnCmd: getEl('warnCmd'),
        broadcastCmd: getEl('broadcastCmd'),
        teleportCmd: getEl('teleportCmd'),
        teamId: getEl('teamId'),
        squadId: getEl('squadId'),
        historyContainer: getEl('historyContainer'),
        toast: getEl('toast'),
        toastMessage: getEl('toastMessage'),
        enableTimestamp: getEl('enableTimestamp'),
        appealPath: getEl('appealPath'),
        enableAppeal: getEl('enableAppeal'),
        slomoValue: getEl('slomoValue')
    };

    // 确保所有必需元素存在
    for (let [key, value] of Object.entries(el)) {
        if (!value) {
            console.error('Missing element:', key);
            document.body.innerHTML = '<div style="color:red; padding:20px;">页面加载错误：缺少元素 ' + key + '，请刷新或联系管理员。</div>';
            return;
        }
    }

    // ==================== 状态 ====================
    let customReasons = [];
    let cmdHistory = [];
    try {
        customReasons = JSON.parse(localStorage.getItem('squad_op_custom_reasons')) || [];
        cmdHistory = JSON.parse(localStorage.getItem('squad_op_history')) || [];
    } catch (e) {}

    // 申诉途径相关状态
    let appealPathText = localStorage.getItem('squad_op_appeal_path') || '';
    let appealEnabled = localStorage.getItem('squad_op_appeal_enabled') === 'true' ? true : false;
    if (appealPathText) el.appealPath.value = appealPathText;
    el.enableAppeal.checked = appealEnabled;

    // ==================== 工具函数 ====================
    const saveCustomReasons = () => {
        localStorage.setItem('squad_op_custom_reasons', JSON.stringify(customReasons));
        refreshReasonSelect();
    };

    const saveHistory = () => {
        localStorage.setItem('squad_op_history', JSON.stringify(cmdHistory));
        updateHistoryUI();
    };

    const saveAppealConfig = () => {
        appealPathText = el.appealPath.value.trim();
        appealEnabled = el.enableAppeal.checked;
        localStorage.setItem('squad_op_appeal_path', appealPathText);
        localStorage.setItem('squad_op_appeal_enabled', appealEnabled);
        showToast('申诉途径已保存', 'success');
        renderAllCmds(); // 刷新预览
    };

    const populateSelect = (selectEl, list, defValue) => {
        selectEl.innerHTML = '';
        list.forEach(item => {
            const label = item.label || item;
            const value = item.value || item;
            const option = new Option(label, value);
            selectEl.add(option);
            if (defValue && (value === defValue || label === defValue)) {
                selectEl.value = value;
            }
        });
    };

    const refreshReasonSelect = () => {
        el.reasonSelect.innerHTML = '<option value="">-- 选择预设 --</option>';
        CONFIG.defaultReasons.forEach(r => el.reasonSelect.add(new Option(r, r)));
        customReasons.forEach(r => {
            if (!CONFIG.defaultReasons.includes(r)) el.reasonSelect.add(new Option(r, r));
        });
    };

    const escapeQuotes = (str) => str ? str.replace(/"/g, '\\\\"') : str;
    
    const getCurrentTimestamp = () => {
        return new Date().toLocaleString('sv-SE').replace(' ', ' ');
    };

    // 构建完整理由（包含处理人、时间戳、申诉途径）
    const buildReason = (baseReason) => {
        const admin = el.adminName.value.trim() || 'Admin';
        const reasonCore = escapeQuotes(baseReason) + ' [处理人:' + admin + ']';
        let finalReason = reasonCore;
        const addTimestamp = el.enableTimestamp.checked;
        if (addTimestamp) {
            finalReason += ' [' + getCurrentTimestamp() + ']';
        }
        if (appealEnabled && appealPathText) {
            finalReason += ' 申诉途径:' + appealPathText;
        }
        return finalReason;
    };

    const highlightCmd = (cmdText) => {
        if (!cmdText) return cmdText;
        const parts = cmdText.match(/^(\\S+)\\s+(.*)$/s);
        if (!parts) return '<span class="cmd-command">' + cmdText + '</span>';
        const command = parts[1];
        let rest = parts[2].replace(/"([^"]*)"/g, '<span class="cmd-string">"$1"</span>');
        return '<span class="cmd-command">' + command + '</span> ' + rest;
    };

    const debounce = (func, wait) => {
        let timeout;
        return (...args) => {
            clearTimeout(timeout);
            timeout = setTimeout(() => func.apply(this, args), wait);
        };
    };

    const showToast = (msg, type = 'success') => {
        el.toastMessage.textContent = msg;
        el.toast.classList.remove('translate-y-[-150%]', 'opacity-0');
        el.toast.classList.add('translate-y-0', 'opacity-100');
        setTimeout(() => {
            el.toast.classList.remove('translate-y-0', 'opacity-100');
            el.toast.classList.add('translate-y-[-150%]', 'opacity-0');
        }, 2000);
    };

    const updateHistoryUI = () => {
        el.historyContainer.innerHTML = '';
        cmdHistory.slice(0, 10).forEach(cmd => {
            const span = document.createElement('span');
            span.className = 'history-item';
            span.textContent = cmd;
            span.onclick = () => copyText(cmd);
            el.historyContainer.appendChild(span);
        });
    };

    // ==================== 动态指令生成（带最新时间戳和申诉途径）====================
    const getCurrentKickCmd = () => {
        const id = el.targetID.value.trim();
        if (!id) return null;
        const rawReason = el.reasonText.value.trim() || '违反服务器规则';
        const reason = buildReason(rawReason);
        return 'Adminkick "' + escapeQuotes(id) + '" ' + reason;
    };

    const getCurrentBanCmd = () => {
        const id = el.targetID.value.trim();
        if (!id) return null;
        const rawReason = el.reasonText.value.trim() || '违反服务器规则';
        const reason = buildReason(rawReason);
        const banTime = el.banTime.value;
        return 'Adminban "' + escapeQuotes(id) + '" ' + banTime + ' ' + reason;
    };

    const getCurrentWarnCmd = () => {
        const id = el.targetID.value.trim();
        if (!id) return null;
        const rawReason = el.reasonText.value.trim() || '违反服务器规则';
        const reason = buildReason(rawReason);
        return 'AdminWarn "' + escapeQuotes(id) + '" ' + reason;
    };

    const getCurrentBroadcastCmd = () => {
        let msg = el.broadcastMsg.value.trim();
        if (!msg) return null;
        const addTimestamp = el.enableTimestamp.checked;
        let finalMsg = msg;
        if (addTimestamp) {
            finalMsg = msg + ' [' + getCurrentTimestamp() + ']';
        }
        return 'AdminBroadcast "' + escapeQuotes(finalMsg) + '"';
    };

    const getCurrentTeleportCmd = () => {
        const name = el.teleportTarget.value.trim();
        if (!name) return null;
        return 'AdminTeleportToPlayer "' + escapeQuotes(name) + '"';
    };

    const getCurrentChangeLayerCmd = () => {
        const map = el.mapSelect.value;
        const mode = el.modeSelect.value;
        const ver = el.verSelect.value;
        const fa = el.facA.value;
        const ka = el.kitA.value;
        const fb = el.facB.value;
        const kb = el.kitB.value;
        const layerStr = map + '_' + mode + '_' + ver + ' ' + fa + '+' + ka + ' ' + fb + '+' + kb;
        return 'AdminChangeLayer ' + layerStr;
    };

    const getCurrentNextLayerCmd = () => {
        const map = el.mapSelect.value;
        const mode = el.modeSelect.value;
        const ver = el.verSelect.value;
        const fa = el.facA.value;
        const ka = el.kitA.value;
        const fb = el.facB.value;
        const kb = el.kitB.value;
        const layerStr = map + '_' + mode + '_' + ver + ' ' + fa + '+' + ka + ' ' + fb + '+' + kb;
        return 'AdminSetNextLayer ' + layerStr;
    };

    // ==================== 复制操作（使用动态生成）====================
    const copyText = async (text) => {
        if (!text || text.includes('...') || (text.includes('<ID>') && !el.targetID.value.trim())) {
            showToast('请填写必要参数', 'warning');
            return;
        }
        try {
            await navigator.clipboard.writeText(text);
            if (!cmdHistory.includes(text)) {
                cmdHistory = [text, ...cmdHistory].slice(0, 10);
                saveHistory();
            }
            showToast('指令已复制');
        } catch {
            const ta = document.createElement('textarea');
            ta.value = text;
            document.body.appendChild(ta);
            ta.select();
            document.execCommand('copy');
            document.body.removeChild(ta);
            if (!cmdHistory.includes(text)) {
                cmdHistory = [text, ...cmdHistory].slice(0, 10);
                saveHistory();
            }
            showToast('指令已复制');
        }
    };

    const copyCmd = (type) => {
        let cmd = null;
        switch(type) {
            case 'kick': cmd = getCurrentKickCmd(); break;
            case 'ban': cmd = getCurrentBanCmd(); break;
            case 'warn': cmd = getCurrentWarnCmd(); break;
            case 'broadcast': cmd = getCurrentBroadcastCmd(); break;
            case 'changeLayer': cmd = getCurrentChangeLayerCmd(); break;
            case 'nextLayer': cmd = getCurrentNextLayerCmd(); break;
            case 'teleport': cmd = getCurrentTeleportCmd(); break;
        }
        if (!cmd) {
            showToast('请填写必要参数', 'warning');
            return;
        }
        copyText(cmd);
    };

    // 新增：复制Slomo指令
    const copySlomo = () => {
        let val = parseFloat(el.slomoValue.value);
        if (isNaN(val)) val = 1;
        val = Math.min(20, Math.max(0, val));
        const cmd = 'AdminSlomo ' + val;
        copyText(cmd);
    };

    // 兼容旧版 copyCmdById
    const copyCmdById = (id) => {
        if (id === 'cmdChangeLayer') copyCmd('changeLayer');
        else if (id === 'cmdNextLayer') copyCmd('nextLayer');
        else if (id === 'teleportCmd') copyCmd('teleport');
        else {
            const raw = document.getElementById(id)?.dataset.raw;
            if (raw) copyText(raw);
        }
    };

    // ==================== 预览渲染 ====================
    const renderAllCmds = () => {
        const admin = el.adminName.value.trim() || 'Admin';
        const id = el.targetID.value.trim() || '<ID>';
        const rawReason = el.reasonText.value.trim() || '违反服务器规则';
        const baseReason = escapeQuotes(rawReason);
        const reasonWithAdmin = baseReason + ' [处理人:' + admin + ']';
        
        const addTimestamp = el.enableTimestamp.checked;
        let timestampSuffix = '';
        if (addTimestamp) {
            timestampSuffix = ' [' + getCurrentTimestamp() + ']';
        }
        let appealSuffix = '';
        if (appealEnabled && appealPathText) {
            appealSuffix = ' 申诉途径:' + appealPathText;
        }
        const finalReasonPreview = reasonWithAdmin + timestampSuffix + appealSuffix;
        
        // 预览 Kick
        const kickRaw = 'Adminkick "' + escapeQuotes(id) + '" ' + finalReasonPreview;
        el.kickCmd.innerHTML = highlightCmd(kickRaw);
        el.kickCmd.dataset.raw = kickRaw;

        // 预览 Ban
        const banRaw = 'Adminban "' + escapeQuotes(id) + '" ' + el.banTime.value + ' ' + finalReasonPreview;
        el.banCmd.innerHTML = highlightCmd(banRaw);
        el.banCmd.dataset.raw = banRaw;

        // 预览 Warn
        const warnRaw = 'AdminWarn "' + escapeQuotes(id) + '" ' + finalReasonPreview;
        el.warnCmd.innerHTML = highlightCmd(warnRaw);
        el.warnCmd.dataset.raw = warnRaw;

        // 广播预览
        let msg = el.broadcastMsg.value.trim();
        let finalMsg = msg;
        if (addTimestamp && msg !== '') {
            finalMsg = msg + ' [' + getCurrentTimestamp() + ']';
        } else if (!msg) {
            finalMsg = '...';
        }
        const broadcastRaw = finalMsg !== '...' ? 'AdminBroadcast "' + escapeQuotes(finalMsg) + '"' : 'AdminBroadcast "..."';
        el.broadcastCmd.innerHTML = highlightCmd(broadcastRaw);
        el.broadcastCmd.dataset.raw = broadcastRaw;

        // 传送预览
        const name = el.teleportTarget.value.trim();
        const escapedName = escapeQuotes(name);
        const teleportRaw = escapedName ? 'AdminTeleportToPlayer "' + escapedName + '"' : 'AdminTeleportToPlayer "..."';
        el.teleportCmd.innerHTML = highlightCmd(teleportRaw);
        el.teleportCmd.dataset.raw = teleportRaw;

        renderLayer();
    };

    const renderLayer = () => {
        const map = el.mapSelect.value;
        const mode = el.modeSelect.value;
        const ver = el.verSelect.value;
        const fa = el.facA.value;
        const ka = el.kitA.value;
        const fb = el.facB.value;
        const kb = el.kitB.value;
        const layerStr = map + '_' + mode + '_' + ver + ' ' + fa + '+' + ka + ' ' + fb + '+' + kb;
        const changeRaw = 'AdminChangeLayer ' + layerStr;
        const nextRaw = 'AdminSetNextLayer ' + layerStr;
        el.cmdChangeLayer.innerHTML = highlightCmd(changeRaw);
        el.cmdChangeLayer.dataset.raw = changeRaw;
        el.cmdNextLayer.innerHTML = highlightCmd(nextRaw);
        el.cmdNextLayer.dataset.raw = nextRaw;
    };

    // ==================== 事件绑定 ====================
    const debouncedRender = debounce(renderAllCmds, 250);

    el.adminName.addEventListener('input', () => {
        localStorage.setItem('squad_op_admin', el.adminName.value.trim());
        debouncedRender();
    });
    el.targetID.addEventListener('input', debouncedRender);
    el.reasonText.addEventListener('input', debouncedRender);
    el.broadcastMsg.addEventListener('input', debouncedRender);
    el.teleportTarget.addEventListener('input', debouncedRender);
    el.banTime.addEventListener('change', renderAllCmds);
    el.enableTimestamp.addEventListener('change', (e) => {
        localStorage.setItem('squad_op_timestamp', e.target.checked);
        renderAllCmds();
    });
    // 申诉途径相关事件
    el.appealPath.addEventListener('input', () => {});
    el.enableAppeal.addEventListener('change', () => {
        renderAllCmds();
    });
    // Slomo输入框改变时无需预览，直接复制即可

    el.reasonSelect.addEventListener('change', (e) => {
        if (e.target.value) {
            el.reasonText.value = e.target.value;
            renderAllCmds();
        }
    });

    el.mapSearch.addEventListener('input', debounce(() => {
        const term = el.mapSearch.value.toLowerCase();
        const sel = el.mapSelect;
        sel.innerHTML = '';
        CONFIG.maps.forEach(map => {
            if (map.label.toLowerCase().includes(term) || map.value.toLowerCase().includes(term)) {
                sel.add(new Option(map.label, map.value));
            }
        });
        renderLayer();
    }, 300));

    ['mapSelect', 'modeSelect', 'verSelect', 'facA', 'kitA', 'facB', 'kitB'].forEach(id => {
        document.getElementById(id).addEventListener('change', renderLayer);
    });

    // ==================== 初始化 ====================
    populateSelect(el.mapSelect, CONFIG.maps, 'Mutaha');
    populateSelect(el.modeSelect, CONFIG.modes, 'RAAS');
    for (let i = 1; i <= 3; i++) {
        el.verSelect.add(new Option('v' + i, 'v' + i));
    }
    populateSelect(el.facA, CONFIG.factions, 'USA');
    populateSelect(el.facB, CONFIG.factions, 'RGF');
    populateSelect(el.kitA, CONFIG.kits, 'CombinedArms');
    populateSelect(el.kitB, CONFIG.kits, 'CombinedArms');
    populateSelect(el.banTime, CONFIG.banTimes, '1d');
    refreshReasonSelect();

    const savedAdmin = localStorage.getItem('squad_op_admin');
    if (savedAdmin) el.adminName.value = savedAdmin;
    const savedReason = localStorage.getItem('squad_op_last_reason');
    if (savedReason) el.reasonText.value = savedReason;
    const savedTimestamp = localStorage.getItem('squad_op_timestamp');
    if (savedTimestamp !== null) {
        el.enableTimestamp.checked = savedTimestamp === 'true';
    } else {
        el.enableTimestamp.checked = true;
        localStorage.setItem('squad_op_timestamp', 'true');
    }

    renderAllCmds();
    updateHistoryUI();

    // ==================== 暴露全局 API ====================
    window.app = {
        copyCmd,
        copyCmdById,
        copyText,
        copySlomo,
        quickCmd: (prefix, inputId) => {
            const val = el[inputId]?.value.trim();
            if (!val) { showToast('请输入目标ID', 'error'); return; }
            copyText(prefix + ' ' + val);
        },
        getDisbandCmd: () => {
            const t = el.teamId.value.trim();
            const s = el.squadId.value.trim();
            if (!t || !s) { showToast('请填写阵营ID和小队ID', 'error'); return; }
            copyText('AdminDisbandSquad ' + t + ' ' + s);
        },
        manageReasons: () => {
            const action = prompt('输入 "add:新理由" 添加, "remove:理由" 删除, 或 "clear" 清空自定义');
            if (!action) return;
            if (action === 'clear') {
                customReasons = [];
                saveCustomReasons();
            } else if (action.startsWith('add:')) {
                const newR = action.substring(4).trim();
                if (newR && !customReasons.includes(newR) && !CONFIG.defaultReasons.includes(newR)) {
                    customReasons.push(newR);
                    saveCustomReasons();
                }
            } else if (action.startsWith('remove:')) {
                const remR = action.substring(7).trim();
                const idx = customReasons.indexOf(remR);
                if (idx !== -1) {
                    customReasons.splice(idx, 1);
                    saveCustomReasons();
                }
            }
        },
        resetConfig: () => {
            if (confirm('确定重置所有设置？')) {
                localStorage.clear();
                location.reload();
            }
        },
        clearHistory: () => {
            cmdHistory = [];
            saveHistory();
        },
        saveAppealConfig
    };
})();
</script>
</body>
</html>`;

    return new Response(html, {
      headers: { "content-type": "text/html;charset=UTF-8" },
    });
  },
};
