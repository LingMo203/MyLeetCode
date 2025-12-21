#!/usr/bin/env python3
"""
LeetCode README 自动生成器
用法：python scripts/update_readme.py
"""

import json
import os
import sys
from datetime import datetime
from collections import Counter, defaultdict
from pathlib import Path

# 添加项目根目录到路径
sys.path.insert(0, str(Path(__file__).parent.parent))

class LeetCodeREADME:
    def __init__(self, data_file='leetcode_data.json'):
        self.data_file = data_file
        self.data = self.load_data()
        self.problems = self.data['problems']

    def load_data(self):
        """加载题目数据"""
        try:
            with open(self.data_file, 'r', encoding='utf-8') as f:
                return json.load(f)
        except FileNotFoundError:
            print(f"错误：找不到数据文件 {self.data_file}")
            print("请确保 leetcode_data.json 在项目根目录")
            sys.exit(1)

    def get_stats(self):
        """获取统计信息"""
        total = len(self.problems)

        # 难度统计
        difficulty_stats = Counter(p['difficulty'] for p in self.problems)

        # 标签统计
        tag_counter = Counter()
        for p in self.problems:
            tag_counter.update(p['tags'])

        return {
            'total': total,
            'difficulties': dict(difficulty_stats),
            'tags': dict(tag_counter),
            'updated_at': datetime.now().strftime('%Y-%m-%d %H:%M:%S')
        }

    def generate_skills_section(self, tag_counter):
        """生成技能点部分"""
        # 按数量排序
        sorted_tags = sorted(tag_counter.items(), key=lambda x: x[1], reverse=True)

        md = "## 🧠 擅长技能与知识点\n\n"

        # 智能分类（根据标签数量动态调整）
        categories = {
            "🔥 核心数据结构": ["数组", "哈希表", "字符串", "链表", "树", "二叉树", "栈"],
            "⚡ 核心算法": ["双指针", "数学", "排序", "动态规划", "二分查找", "贪心",
                           "深度优先搜索", "广度优先搜索", "递归", "位运算"],
            "🛠️ 其他技能": ["设计", "滑动窗口", "分治", "单调栈", "模拟",
                            "并查集", "回溯", "快速选择", "随机化", "枚举", "队列", "矩阵"]
        }

        for category, tags in categories.items():
            md += f"### {category}\n"
            found_tags = [(tag, tag_counter[tag]) for tag in tags if tag in tag_counter]
            if found_tags:
                for tag, count in sorted(found_tags, key=lambda x: x[1], reverse=True):
                    md += f"- **{tag}** x{count}\n"
            md += "\n"

        return md

    def generate_problems_table(self):
        """生成题目表格"""
        md = "## 📋 已解决题目列表\n\n"
        md += "| 序号 | 题目 | 难度 | 标签 | 解决日期 |\n"
        md += "|:---:|:---|:---:|:---|:---|\n"

        # 按题号排序
        sorted_problems = sorted(self.problems, key=lambda x: x['id'])

        for problem in sorted_problems:
            # 题目链接
            title = problem['title']
            url = problem.get('leetcode_url', f"https://leetcode.cn/problems/{problem['title_slug']}/")
            title_link = f"[{title}]({url})"

            # 难度表情
            difficulty_map = {"简单": "🟢", "中等": "🟡", "困难": "🔴"}
            difficulty_emoji = difficulty_map.get(problem['difficulty'], "⚪")
            difficulty_text = f"{difficulty_emoji} {problem['difficulty']}"

            # 标签 - 显示全部标签
            tags = problem['tags']
            tags_text = "、".join(tags)  # 直接使用所有标签

            # 解决日期
            dates = problem['solved_dates']
            if dates:
                if len(dates) > 1:
                    dates_text = f"**{len(dates)}次**<br>" + "<br>".join(dates[-2:])
                else:
                    dates_text = dates[0]
            else:
                dates_text = ""

            md += f"| {problem['id']} | {title_link} | {difficulty_text} | {tags_text} | {dates_text} |\n"

        return md

    def generate_complete_readme(self):
        """生成完整的README"""
        stats = self.get_stats()

        # LeetCode统计卡片
        leetcode_card = f"""
## 📈 刷题统计
![LeetCode Stats](https://leetcard.jacoblin.cool/{self.data.get('username', 'lingmo203')}?theme=unicorn&font=Anek%20Devanagari&ext=activity&site=cn)

**总题数：{stats['total']}**  
🟢 简单：{stats['difficulties'].get('简单', 0)}  
🟡 中等：{stats['difficulties'].get('中等', 0)}  
🔴 困难：{stats['difficulties'].get('困难', 0)}
"""

        # 技能点部分
        skills_section = self.generate_skills_section(stats['tags'])

        # 题目表格
        problems_section = self.generate_problems_table()

        # 页脚
        footer = f"""
---

## 🔄 更新记录
- **最后更新**：{stats['updated_at']}
- **总题目数**：{stats['total']}
- **技能标签数**：{len(stats['tags'])}

> 🤖 本页面由自动化脚本生成
> 
> 📝 维护方式：运行 `python scripts/add_problem.py` 更新 `leetcode_data.json` 后运行 `python scripts/update_readme.py`
"""

        # 组合所有部分
        readme_content = f"""# 🎯 LeetCode Solutions

此项目为本人LeetCode练习记录，通过自动化脚本维护。

{leetcode_card}

---

{skills_section}

---

{problems_section}

{footer}
"""

        return readme_content

    def update_readme(self):
        """更新README.md文件"""
        readme_content = self.generate_complete_readme()

        with open('README.md', 'w', encoding='utf-8') as f:
            f.write(readme_content)

        print("✅ README.md 已成功更新！")
        print(f"📊 统计信息：")
        print(f"   总题数: {len(self.problems)}")
        print(f"   简单: {sum(1 for p in self.problems if p['difficulty'] == '简单')}")
        print(f"   中等: {sum(1 for p in self.problems if p['difficulty'] == '中等')}")
        print(f"   困难: {sum(1 for p in self.problems if p['difficulty'] == '困难')}")
        print(f"   技能标签: {len(self.get_stats()['tags'])}")
        print(f"⏰ 更新时间: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}")

def main():
    """主函数"""
    print("🚀 LeetCode README 生成器")
    print("=" * 40)

    generator = LeetCodeREADME()
    generator.update_readme()

    print("=" * 40)
    print("✅ 完成！请查看 README.md 文件")

if __name__ == "__main__":
    main()